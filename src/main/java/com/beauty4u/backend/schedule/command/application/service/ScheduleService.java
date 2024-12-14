package com.beauty4u.backend.schedule.command.application.service;

import com.beauty4u.backend.schedule.command.application.dto.CreateScheduleReqDTO;
import com.beauty4u.backend.schedule.command.domain.service.ScheduleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleDomainService scheduleDomainService;

    @Transactional
    public void saveSchedule(String loginUserCode, CreateScheduleReqDTO createScheduleReqDTO) {

        scheduleDomainService.saveSchedule(loginUserCode, createScheduleReqDTO);
    }
}
