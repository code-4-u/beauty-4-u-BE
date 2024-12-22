package com.beauty4u.backend.goods.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoodsListResDTO {
    private List<GoodsQueryDTO> goodsList;
    private Long totalCount;
}
