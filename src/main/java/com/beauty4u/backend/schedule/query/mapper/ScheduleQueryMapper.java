package com.beauty4u.backend.schedule.query.mapper;

import com.beauty4u.backend.schedule.query.dto.ScheduleListResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleQueryMapper {

    List<ScheduleListResDTO> findScheduleList();
}
