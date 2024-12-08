package com.beauty4u.backend.inform.command.application.service;

import com.beauty4u.backend.inform.command.application.dto.CreateInformReqDTO;
import com.beauty4u.backend.inform.command.domain.service.InformDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InformCommandService {

    private final InformDomainService informDomainService;

    @Transactional
    public void saveInform(String loginUserCode, CreateInformReqDTO createInformReqDTO) {

        informDomainService.saveInform(loginUserCode, createInformReqDTO);
    }
}
