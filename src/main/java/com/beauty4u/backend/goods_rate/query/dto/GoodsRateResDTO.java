package com.beauty4u.backend.goods_rate.query.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GoodsRateResDTO {
    private List<GoodsRateQueryDTO> increase;
    private List<GoodsRateQueryDTO> decrease;
}
