package com.beauty4u.backend.noti.command.application.service;

import com.beauty4u.backend.noti.command.domain.service.NotiDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class NotiService {

    private final NotiDomainService notiDomainService;

    public SseEmitter subscribe(String loginUserCode, String lastEventId) {

        return notiDomainService.subscribe(loginUserCode, lastEventId);
    }
}
