package com.beauty4u.backend.user.query.mapper;

import com.beauty4u.backend.user.query.dto.FindUserDetailResDTO;
import com.beauty4u.backend.user.query.dto.UserListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserQueryMapper {

    List<UserListDTO> findUserList(
            @Param("offset") Long offset,
            @Param("count") Long count,
            @Param("search") String search
    );

    FindUserDetailResDTO findUserDetail(@Param("userCode") String userCode);

    Long findUserListCount(@Param("search") String search);
}
