package com.beauty4u.backend.user.query.mapper;

import com.beauty4u.backend.user.query.dto.role.RoleResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleQueryMapper {

    List<RoleResDTO> findRoleList();
}
