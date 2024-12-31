package com.beauty4u.backend.opensearch.document;

import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import com.beauty4u.backend.opensearch.common.BaseSearchDocument;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Document(indexName = "${spring.opensearch.goods.index.name}")
public class GoodsDocument extends BaseSearchDocument {

    //    @Field(name = "goods_name", type = FieldType.Text, analyzer = "korean")
    private String goodsName;

    public static GoodsDocument from(GoodsQueryDTO goodsQueryDTO) {
        GoodsDocument goodsDocument = GoodsDocument.builder()
                .goodsName(goodsQueryDTO.getGoodsName())
                .build();
        goodsDocument.setId(goodsQueryDTO.getGoodsCode());
        return goodsDocument;
    }
}
