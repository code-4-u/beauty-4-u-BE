package com.beauty4u.backend.goods.query.elasticsearch.document;

import com.beauty4u.backend.elasticsearch.BaseSearchDocument;
import com.beauty4u.backend.goods.query.dto.ReviewQueryDTO;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "reviews") // reviews 인덱스(=DB table 개념)에 데이터 저장
public class ReviewDocument extends BaseSearchDocument {

    @Field(name = "review_content", type = FieldType.Text)
    private String reviewContent;

    public static ReviewDocument from(ReviewQueryDTO reviewQueryDTO) {
        ReviewDocument reviewDocument = ReviewDocument.builder()
                .reviewContent(reviewQueryDTO.getReviewContent())
                .build();
        reviewDocument.setId(reviewDocument.getId());
        return reviewDocument;
    }
}
