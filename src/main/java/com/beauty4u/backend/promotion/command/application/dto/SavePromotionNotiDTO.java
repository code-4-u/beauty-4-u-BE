package com.beauty4u.backend.promotion.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePromotionNotiDTO {
    private Integer promotionNotiId;
    private String customerCode;
    private String promotionNotiContent;
    private LocalDateTime promotionNotiSentDate;
}
