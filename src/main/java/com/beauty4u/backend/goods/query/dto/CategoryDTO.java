package com.beauty4u.backend.goods.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// 상위 카테고리 클릭 시에 하위 카테고리 + 상위에 해당하는 상품 목록 조회를 위한 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private List<GoodsQueryDTO> goods;
    private List<SubCategoryDTO> subCategory;
}
