package com.beauty4u.backend.goods.query.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSortDTO {
    private String primarySort = "created_date"; // 첫번째 정렬 기준
    private String primaryOrder = "DESC"; // 첫번째 정렬 순서
    private String secondarySort = null; // 두번째 정렬 기준
    private String secondaryOrder = null; // 두번째 정렬 순서
}
