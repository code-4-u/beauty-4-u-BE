package com.beauty4u.backend.user.query.service;

import com.beauty4u.backend.user.query.dto.UserListResDTO;
import com.beauty4u.backend.user.query.mapper.UserQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserQueryMapper userQueryMapper;

    public List<UserListResDTO> findUserList(Long page, Long count) {

        long offset = (page - 1) * count;

        return userQueryMapper.findUserList(offset, count);
    }
}
