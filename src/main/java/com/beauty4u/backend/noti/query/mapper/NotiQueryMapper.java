package com.beauty4u.backend.noti.query.mapper;

import com.beauty4u.backend.noti.query.dto.NotiListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotiQueryMapper {

    List<NotiListResDTO> findNotiList(@Param("userCode") String userCode);
}
