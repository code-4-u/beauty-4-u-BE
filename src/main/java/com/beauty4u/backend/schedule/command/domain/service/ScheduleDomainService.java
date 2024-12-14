package com.beauty4u.backend.schedule.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.schedule.command.application.dto.ScheduleReqDTO;
import com.beauty4u.backend.schedule.command.domain.aggregate.ScheduleInfo;
import com.beauty4u.backend.schedule.command.domain.repository.ScheduleRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleDomainService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Long saveSchedule(String loginUserCode, ScheduleReqDTO scheduleReqDTO) {

        String url = "/team/{deptCode}";

        ScheduleInfo schedule = modelMapper.map(scheduleReqDTO, ScheduleInfo.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        schedule.createTeamSchedule(user, url);

        ScheduleInfo savedSchedule = scheduleRepository.save(schedule);

        return savedSchedule.getId();
    }

    public void updateSchedule(Long scheduleId, ScheduleReqDTO scheduleReqDTO) {

        String title = scheduleReqDTO.getScheduleTitle();
        String content = scheduleReqDTO.getScheduleContent();
        String url = "/team/test";
        LocalDateTime start = scheduleReqDTO.getScheduleStart();
        LocalDateTime end = scheduleReqDTO.getScheduleEnd();

        ScheduleInfo schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SCHEDULE));

        schedule.modifySchedule(title, content, url, start, end);
    }

    public void deleteSchedule(Long scheduleId) {

        ScheduleInfo schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SCHEDULE));

        scheduleRepository.delete(schedule);
    }
}
