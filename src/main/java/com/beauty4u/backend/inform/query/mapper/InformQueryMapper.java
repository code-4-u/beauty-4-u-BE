package com.beauty4u.backend.inform.query.mapper;

import com.beauty4u.backend.inform.query.dto.InformDetailResDTO;
import com.beauty4u.backend.inform.query.dto.InformListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InformQueryMapper {

    List<InformListResDTO> findInformList(
            @Param("offset") Long offset,
            @Param("count") Long count);

    InformDetailResDTO findInformDetail(@Param("informId") Long informId);
}
