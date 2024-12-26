package com.beauty4u.backend.file.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileQueryMapper {

    List<String> findFileList(
            @Param("fileUrl") String fileUrl);

    Long findFileCount(
            @Param("fileUrl") String fileUrl);
}
