package com.beauty4u.backend.goods.query.opensearch.document;

import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodsDocument {

    private String id;

    private String goodsName;

    public static GoodsDocument from(GoodsQueryDTO goodsQueryDTO) {
        return GoodsDocument.builder()
                .id(goodsQueryDTO.getGoodsCode())
                .goodsName(goodsQueryDTO.getGoodsName())
                .build();
    }
}