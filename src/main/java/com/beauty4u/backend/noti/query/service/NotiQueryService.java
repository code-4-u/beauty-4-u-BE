package com.beauty4u.backend.noti.query.service;

import com.beauty4u.backend.noti.query.dto.NotiListResDTO;
import com.beauty4u.backend.noti.query.mapper.NotiQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotiQueryService {

    private final NotiQueryMapper notiQueryMapper;

    @Transactional(readOnly = true)
    public List<NotiListResDTO> findNotiList(String loginUserCode) {

        return notiQueryMapper.findNotiList(loginUserCode);
    }
}
