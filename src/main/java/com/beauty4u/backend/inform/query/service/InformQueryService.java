package com.beauty4u.backend.inform.query.service;

import com.beauty4u.backend.inform.query.dto.InformDetailResDTO;
import com.beauty4u.backend.inform.query.dto.InformListResDTO;
import com.beauty4u.backend.inform.query.mapper.InformQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InformQueryService {

    private final InformQueryMapper informQueryMapper;

    @Transactional(readOnly = true)
    public List<InformListResDTO> findInformList(Long page, Long count) {

        long offset = (page - 1) * count;

        return informQueryMapper.findInformList(offset, count);
    }

    @Transactional(readOnly = true)
    public InformDetailResDTO findInformDetail(Long informId) {

        return informQueryMapper.findInformDetail(informId);
    }
}
