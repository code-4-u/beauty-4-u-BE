package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FindPromotionListResDTO {
    private Integer promotionCount;
    private List<FindPromotionResDTO> findPromotionResList;
}
