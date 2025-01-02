package com.beauty4u.backend.schedule.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.schedule.command.application.dto.CreateScheduleReqDTO;
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

    public Long saveSchedule(String loginUserCode, CreateScheduleReqDTO createScheduleReqDTO) {

        String type = createScheduleReqDTO.getScheduleType();
        String url = createScheduleReqDTO.getScheduleUrl();

        ScheduleInfo schedule = modelMapper.map(createScheduleReqDTO.getScheduleReqDTO(), ScheduleInfo.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        // 팀 일정
        if (type.equals("TEAMSCHEDULE")) {
            schedule.createTeamSchedule(user, "팀 일정");
        } else { // 프로모션 일정
            schedule.createPromotionSchedule(user, url);
        }

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
