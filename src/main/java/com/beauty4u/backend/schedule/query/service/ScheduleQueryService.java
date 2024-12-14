package com.beauty4u.backend.schedule.query.service;

import com.beauty4u.backend.schedule.query.dto.ScheduleListResDTO;
import com.beauty4u.backend.schedule.query.mapper.ScheduleQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleQueryService {

    private final ScheduleQueryMapper scheduleQueryMapper;

    @Transactional(readOnly = true)
    public List<ScheduleListResDTO> findScheduleList(String loginUserCode) {

        return scheduleQueryMapper.findScheduleList();
    }
}
