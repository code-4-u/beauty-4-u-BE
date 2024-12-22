package com.beauty4u.backend.goods.query.elasticsearch.document;

import com.beauty4u.backend.elasticsearch.BaseSearchDocument;
import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "goods")
@Setting(settingPath = "/elasticsearch/settings/settings.json")
@Mapping(mappingPath = "/elasticsearch/mappings/mappings.json")
public class GoodsDocument extends BaseSearchDocument {

    @Field(name = "goods_name", type = FieldType.Text, analyzer = "korean")
    private String goodsName;

    public static GoodsDocument from(GoodsQueryDTO goodsQueryDTO) {
        GoodsDocument goodsDocument = GoodsDocument.builder()
                .goodsName(goodsQueryDTO.getGoodsName())
                .build();
        goodsDocument.setId(goodsQueryDTO.getGoodsCode());
        return goodsDocument;
    }
}
