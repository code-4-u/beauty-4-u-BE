package com.beauty4u.backend.inform.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inform.command.application.dto.CreateInformReqDTO;
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

    public void saveInform(String loginUserCode, CreateInformReqDTO createInformReqDTO) {

        Inform inform = modelMapper.map(createInformReqDTO, Inform.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        inform.modifyUser(user);

        try {
            informRepository.save(inform);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVED_INFORM);
        }
    }
}
