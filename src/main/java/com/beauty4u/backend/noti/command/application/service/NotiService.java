package com.beauty4u.backend.noti.command.application.service;

import com.beauty4u.backend.noti.command.application.dto.NotiIdReqDTO;
import com.beauty4u.backend.noti.command.application.dto.NotiIdReqDTOList;
import com.beauty4u.backend.noti.command.domain.service.NotiDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class NotiService {

    private final NotiDomainService notiDomainService;

    public SseEmitter subscribe(String loginUserCode, String lastEventId) {

        return notiDomainService.subscribe(loginUserCode, lastEventId);
    }

    @Transactional
    public void updateNotiRead(NotiIdReqDTO notiIdReqDTO) {

        Long notiId = notiIdReqDTO.getNotiId();

        notiDomainService.updateNotiRead(notiId);
    }

    @Transactional
    public void updateNotiListRead(NotiIdReqDTOList notiIdReqDTOList) {

        notiDomainService.updateNotiReadList(notiIdReqDTOList.getNotiIdList());
    }
}
