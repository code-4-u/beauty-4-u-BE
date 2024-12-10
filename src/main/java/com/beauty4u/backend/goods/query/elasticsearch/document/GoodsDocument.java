package com.beauty4u.backend.goods.query.elasticsearch.document;

import com.beauty4u.backend.elasticsearch.BaseSearchDocument;
import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "goods")
public class GoodsDocument extends BaseSearchDocument {

    @Field(name = "goods_name", type = FieldType.Text)
    private String goodsName;

    public static GoodsDocument from(GoodsQueryDTO goodsQueryDTO) {
        GoodsDocument goodsDocument = GoodsDocument.builder()
                .goodsName(goodsQueryDTO.getGoodsName())
                .build();
        goodsDocument.setId(goodsQueryDTO.getGoodsCode());
        return goodsDocument;
    }
}
