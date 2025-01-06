package com.beauty4u.backend.marketing.query.mapper;

import com.beauty4u.backend.marketing.query.dto.MarketingSettingQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarketingSettingQueryMapper {
    // 전체 마케팅 알람 목록 조회
    List<MarketingSettingQueryDTO> findAllMarketingSetting();
}
