package com.beauty4u.backend.user.query.mapper;

import com.beauty4u.backend.user.query.dto.job.JobResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobQueryMapper {

    List<JobResDTO> findJobList();
}
