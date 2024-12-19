package com.beauty4u.backend.goods_rate.query.service;

import com.beauty4u.backend.common.aggregate.PeriodType;
import com.beauty4u.backend.goods_rate.query.dto.GoodsRateQueryDTO;
import com.beauty4u.backend.goods_rate.query.dto.GoodsRateResDTO;
import com.beauty4u.backend.goods_rate.query.mapper.GoodsRateQueryMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GoodsRateQueryService {

    private static final Logger log = LoggerFactory.getLogger(GoodsRateQueryService.class);
    private final GoodsRateQueryMapper goodsRateQueryMapper;

    public List<GoodsRateQueryDTO> findGoodsRate(LocalDateTime startDate, LocalDateTime endDate, PeriodType periodType) {

//        return goodsRateQueryMapper.findGoodsRate(startDate, endDate, periodType.name());

        log.info("조회 시작 - startDate: {}, endDate: {}, periodType: {}", startDate, endDate, periodType);
        List<GoodsRateQueryDTO> result = goodsRateQueryMapper.findGoodsRate(startDate, endDate, periodType.name());
        log.info("조회 결과 크기: {}", result.size());
        return result;
    }
}
