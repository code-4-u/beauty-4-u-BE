package com.beauty4u.backend.inform.query.service;

import com.beauty4u.backend.inform.query.dto.InformDetailResDTO;
import com.beauty4u.backend.inform.query.dto.InformFilterDTO;
import com.beauty4u.backend.inform.query.dto.InformListDTO;
import com.beauty4u.backend.inform.query.dto.InformListResDTO;
import com.beauty4u.backend.inform.query.mapper.InformQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InformQueryService {

    private final InformQueryMapper informQueryMapper;

    @Transactional(readOnly = true)
    public InformListResDTO findInformList(InformFilterDTO informFilterDTO) {

        long offset = (informFilterDTO.getPage() - 1) * informFilterDTO.getCount();

        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null; // 종료일의 23:59:59

        if (informFilterDTO.getStartDate() != null) {
            startDateTime = informFilterDTO.getStartDate().atStartOfDay();
        }

        if (informFilterDTO.getEndDate() != null) {
            endDateTime = informFilterDTO.getEndDate().atStartOfDay();
        }

        List<InformListDTO> informListDTO = informQueryMapper.findInformList(
                informFilterDTO.getInformTitle(),
                informFilterDTO.getPublishStatus(),
                startDateTime,
                endDateTime,
                informFilterDTO.getSort(),
                informFilterDTO.getOrder(),
                offset,
                informFilterDTO.getCount());

        InformListResDTO informListResDTO = new InformListResDTO();
        informListResDTO.setInformList(informListDTO);

        Long totalCount = informQueryMapper.findInformListTotalCount(
                informFilterDTO.getInformTitle(),
                informFilterDTO.getPublishStatus(),
                startDateTime,
                endDateTime,
                informFilterDTO.getSort(),
                informFilterDTO.getOrder()
        );
        informListResDTO.setTotalCount(totalCount);

        return informListResDTO;
    }

    @Transactional(readOnly = true)
    public InformDetailResDTO findInformDetail(Long informId) {

        return informQueryMapper.findInformDetail(informId);
    }
}
