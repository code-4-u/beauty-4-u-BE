package com.beauty4u.backend.schedule.command.application.service;

import com.beauty4u.backend.noti.command.domain.aggregate.NotiType;
import com.beauty4u.backend.noti.command.domain.service.NotiDomainService;
import com.beauty4u.backend.schedule.command.application.dto.CreateScheduleReqDTO;
import com.beauty4u.backend.schedule.command.application.dto.ScheduleReqDTO;
import com.beauty4u.backend.schedule.command.domain.service.ScheduleDomainService;
import com.beauty4u.backend.user.command.domain.service.UserDomainService;
import com.beauty4u.backend.user.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleDomainService scheduleDomainService;
    private final NotiDomainService notiDomainService;
    private final UserQueryService userQueryService;
    private final UserDomainService userDomainService;

    @Transactional
    public Long saveSchedule(String loginUserCode, String loginDeptCode, CreateScheduleReqDTO createScheduleReqDTO) {

        NotiType notiType = NotiType.valueOf(createScheduleReqDTO.getScheduleType());

        Long scheduleId = scheduleDomainService.saveSchedule(loginUserCode, createScheduleReqDTO);

        String userName = userDomainService.findByUserCode(loginUserCode);

        List<String> targetUserCodeList = null;
        String notiMessage = "";

        // 팀 일정 알림
        if (notiType.equals(NotiType.TEAMSCHEDULE)) {
            targetUserCodeList = userQueryService.findDeptUserList(loginDeptCode);
            notiMessage += userName + " (님이) " + notiType.getMessage();
        } else { // 프로모션 일정 알림
            targetUserCodeList = userDomainService.findAllUser();
            notiMessage = createScheduleReqDTO.getScheduleReqDTO().getScheduleTitle() + " " + notiType.getMessage();
        }

        List<String> mutableList = new ArrayList<>(targetUserCodeList);
        mutableList.remove(loginUserCode);

        notiDomainService.send(loginUserCode, mutableList, notiType, notiMessage, createScheduleReqDTO.getScheduleUrl());

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
