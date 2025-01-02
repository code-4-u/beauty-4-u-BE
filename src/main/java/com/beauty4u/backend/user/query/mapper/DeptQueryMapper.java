package com.beauty4u.backend.user.query.mapper;

import com.beauty4u.backend.user.query.dto.dept.DeptResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptQueryMapper {

    List<DeptResDTO> findDeptList();

    DeptResDTO findDeptName(String deptCode);
}
