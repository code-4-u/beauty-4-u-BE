package com.beauty4u.backend.user.query.mapper;

import com.beauty4u.backend.user.query.dto.FindUserDetailResDTO;
import com.beauty4u.backend.user.query.dto.UserListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserQueryMapper {

    List<UserListResDTO> findUserList(
            @Param("offset") Long offset,
            @Param("count") Long count);

    FindUserDetailResDTO findUserDetail(@Param("userCode") String userCode);
}
