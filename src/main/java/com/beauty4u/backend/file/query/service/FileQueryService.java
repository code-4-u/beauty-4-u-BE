package com.beauty4u.backend.file.query.service;

import com.beauty4u.backend.file.query.dto.FileListResDTO;
import com.beauty4u.backend.file.query.mapper.FileQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileQueryService {

    private final FileQueryMapper fileQueryMapper;

    @Transactional(readOnly = true)
    public FileListResDTO findFileList(String fileUrl) {

        List<String> fileList = fileQueryMapper.findFileList(fileUrl);
        Long totalCount = fileQueryMapper.findFileCount(fileUrl);

        FileListResDTO fileListResDTO = new FileListResDTO();
        fileListResDTO.setFileList(fileList);
        fileListResDTO.setTotalCount(totalCount);

        return fileListResDTO;
    }
}
