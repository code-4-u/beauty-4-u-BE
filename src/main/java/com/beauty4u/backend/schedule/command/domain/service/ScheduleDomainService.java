package com.beauty4u.backend.schedule.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.schedule.command.application.dto.CreateScheduleReqDTO;
import com.beauty4u.backend.schedule.command.domain.aggregate.ScheduleInfo;
import com.beauty4u.backend.schedule.command.domain.repository.ScheduleRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleDomainService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void saveSchedule(String loginUserCode, CreateScheduleReqDTO createScheduleReqDTO) {

        ScheduleInfo schedule = modelMapper.map(createScheduleReqDTO, ScheduleInfo.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        schedule.modifyUser(user);

        scheduleRepository.save(schedule);
    }
}
