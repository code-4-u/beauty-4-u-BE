package com.beauty4u.backend.schedule.command.application.service;

import com.beauty4u.backend.schedule.command.application.dto.ScheduleReqDTO;
import com.beauty4u.backend.schedule.command.domain.service.ScheduleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleDomainService scheduleDomainService;

    @Transactional
    public void saveSchedule(String loginUserCode, ScheduleReqDTO scheduleReqDTO) {

        scheduleDomainService.saveSchedule(loginUserCode, scheduleReqDTO);
    }

    @Transactional
    public void updateSchedule(Long scheduleId, ScheduleReqDTO scheduleReqDTO) {

        scheduleDomainService.updateSchedule(scheduleId, scheduleReqDTO);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {

        scheduleDomainService.deleteSchedule(scheduleId);
    }
}
