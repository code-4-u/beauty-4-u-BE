package com.beauty4u.backend.inform.command.application.service;

import com.beauty4u.backend.file.command.domain.service.FileDomainService;
import com.beauty4u.backend.common.util.S3ImageUtil;
import com.beauty4u.backend.inform.command.application.dto.InformReqDTO;
import com.beauty4u.backend.inform.command.application.dto.UpdateInformViewcount;
import com.beauty4u.backend.inform.command.domain.service.InformDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InformService {

    private final InformDomainService informDomainService;
    private final FileDomainService fileDomainService;
    private final S3ImageUtil s3ImageUtil;

    @Transactional
    public Long saveInform(String loginUserCode, InformReqDTO informReqDTO) {

        return informDomainService.saveInform(loginUserCode, informReqDTO);
    }

    @Transactional
    public void updateInform(Long informId, InformReqDTO informReqDTO) {

        informDomainService.updateInform(informId, informReqDTO);
    }

    @Transactional
    public void deleteInform(Long informId) {

        informDomainService.deleteInform(informId);
    }

    @Transactional
    public void updateInformViewCount(Long informId, UpdateInformViewcount informViewcount) {

        informDomainService.updateInformViewcount(informId, informViewcount);
    }
}
