package com.beauty4u.backend.promotion.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavePromotionReqDTO {

    @NotNull(message = "프로모션 종류 id를 입력하세요")
    private Long promotionTypeId;

    @NotNull(message = "제목을 입력하세요")
    private String promotionTitle;

    @NotNull(message = "내용을 입력하세요")
    private String promotionContent;

    @NotNull(message = "시작일을 입력하세요")
    private LocalDateTime promotionStartDate;

    @NotNull(message = "종료일을 입력하세요")
    private LocalDateTime promotionEndDate;

    private String promotionStatus;
}
