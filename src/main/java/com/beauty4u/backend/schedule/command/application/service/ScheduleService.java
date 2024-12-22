package com.beauty4u.backend.schedule.command.application.service;

import com.beauty4u.backend.noti.command.domain.aggregate.NotiType;
import com.beauty4u.backend.noti.command.domain.service.NotiDomainService;
import com.beauty4u.backend.schedule.command.application.dto.CreateScheduleReqDTO;
import com.beauty4u.backend.schedule.command.application.dto.ScheduleReqDTO;
import com.beauty4u.backend.schedule.command.domain.service.ScheduleDomainService;
import com.beauty4u.backend.user.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleDomainService scheduleDomainService;
    private final NotiDomainService notiDomainService;
    private final UserQueryService userQueryService;

    @Transactional
    public Long saveSchedule(String loginUserCode, String loginDeptCode, CreateScheduleReqDTO createScheduleReqDTO) {

        Long scheduleId = scheduleDomainService.saveSchedule(loginUserCode, createScheduleReqDTO);

        List<String> userList = userQueryService.findDeptUserList(loginDeptCode);

        notiDomainService.send(loginUserCode, userList, NotiType.TEAMSPACE, NotiType.TEAMSPACE.getMessage(), createScheduleReqDTO.getScheduleUrl());

        return scheduleId;
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
