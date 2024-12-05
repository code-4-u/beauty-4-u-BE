package com.beauty4u.backend.goods.query.elasticsearch.document;

import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "goods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDocument {
    @Id
    @Field(name = "goods_code", type = FieldType.Text)
    private String goodsCode;

    @Field(name = "goods_name", type = FieldType.Text)
    private String goodsName;

    public static GoodsDocument from(GoodsQueryDTO goodsQueryDTO) {
        return GoodsDocument.builder()
                .goodsCode(goodsQueryDTO.getGoodsCode())
                .goodsName(goodsQueryDTO.getGoodsName())
                .build();
    }
}
