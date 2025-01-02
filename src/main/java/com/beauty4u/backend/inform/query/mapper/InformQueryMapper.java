package com.beauty4u.backend.inform.query.mapper;

import com.beauty4u.backend.inform.query.dto.InformDetailResDTO;
import com.beauty4u.backend.inform.query.dto.InformListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface InformQueryMapper {

    List<InformListDTO> findInformList(
            @Param("informTitle") String informTitle,
            @Param("publishStatus") String publishStatus,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count
    );

    Long findInformListTotalCount(
            @Param("informTitle") String informTitle,
            @Param("publishStatus") String publishStatus,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("sort") String sort,
            @Param("order") String order
    );

    InformDetailResDTO findInformDetail(@Param("informId") Long informId);
}
