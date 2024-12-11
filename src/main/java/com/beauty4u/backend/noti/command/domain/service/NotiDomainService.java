package com.beauty4u.backend.noti.command.domain.service;

import com.beauty4u.backend.common.aggregate.YnType;
import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inquiry.command.domain.repository.InquiryRepository;
import com.beauty4u.backend.noti.command.domain.aggregate.Noti;
import com.beauty4u.backend.noti.command.domain.aggregate.NotiType;
import com.beauty4u.backend.noti.command.domain.repository.NotiRepository;
import com.beauty4u.backend.noti.command.domain.repository.SseRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotiDomainService {

    private final SseRepository sseRepository;
    private final NotiRepository notiRepository;
    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;

    /* 연결 지속시간 : 한 시간 */
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    public SseEmitter subscribe(String loginUserCode, String lastEventId) {

        String emitterId = loginUserCode + "_" + System.currentTimeMillis();
        SseEmitter emitter = sseRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion(() -> sseRepository.deleteById(emitterId));
        emitter.onTimeout(() -> sseRepository.deleteById(emitterId));

        // 연결 성공 시 더미 이벤트 전송
        sendToClient(emitter, emitterId, "EventStream Created. [userCode=" + loginUserCode + "]");

        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = sseRepository.findAllEventCacheStartWithByUserCode(loginUserCode);
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }

    /* 알림 등록 및 SSE 알림 전송 */
    public void send(String senderCode, List<String> receiverCodes, NotiType notiType, String url) {

        UserInfo sender = userRepository.findByUserCode(senderCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        List<UserInfo> receivers = userRepository.findAllByUserCodeIn(receiverCodes);

        if (receivers.size() != receiverCodes.size()) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        // 알림 생성 및 저장
        List<Noti> notifications = receivers.stream()
                .map(receiver -> Noti.builder()
                        .notiReceiver(receiver)
                        .notiType(notiType)
                        .notiContent(notiType.getMessage())
                        .notiUrl(url)
                        .notiReadYn(YnType.N)
                        .notiSender(sender)
                        .build())
                .toList();

        notiRepository.saveAll(notifications);

        // SSE 알림 전송
        notifications.forEach(noti -> {
            String receiverCode = noti.getNotiReceiver().getUserCode();
            String eventId = receiverCode + "_" + System.currentTimeMillis();
            Map<String, SseEmitter> sseEmitters = sseRepository.findAllEmitterStartWithByUserCode(receiverCode);

            sseEmitters.forEach((key, emitter) -> {
                sseRepository.saveEventCache(eventId, noti);
                sendToClient(emitter, eventId, noti);
            });
        });
    }

    private void sendToClient(SseEmitter emitter, String emitterId, Object data) {

        try {
            emitter.send(SseEmitter.event()
                    .id(emitterId)
                    .data(data));
        } catch (IOException e) {
            sseRepository.deleteById(emitterId);
            throw new CustomException(ErrorCode.NOT_REQUEST_NOTI);
        }
    }

    public void updateNotiRead(Long notiId) {

        Noti noti = notiRepository.findById(notiId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_NOTI));

        noti.readNoti();
    }
}
