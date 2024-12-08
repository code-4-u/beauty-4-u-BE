package com.beauty4u.backend.inform.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inform.command.application.dto.InformReqDTO;
import com.beauty4u.backend.inform.command.domain.aggregate.Inform;
import com.beauty4u.backend.inform.command.domain.repository.InformRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InformDomainService {

    private final InformRepository informRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void saveInform(String loginUserCode, InformReqDTO informReqDTO) {

        Inform inform = modelMapper.map(informReqDTO, Inform.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        inform.modifyUser(user);

        try {
            informRepository.save(inform);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVED_INFORM);
        }
    }

    public void updateInform(Long informId, InformReqDTO updateInformReqDTO) {

        String title = updateInformReqDTO.getInformTitle();
        String content = updateInformReqDTO.getInformContent();

        Inform inform = informRepository.findById(informId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INFORM));

        inform.modifyInform(title, content);
    }

    public void deleteInform(Long informId) {

        Inform inform = informRepository.findById(informId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INFORM));

        informRepository.delete(inform);
    }
}
