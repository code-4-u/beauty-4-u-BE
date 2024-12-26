package com.beauty4u.backend.promotion.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PromotionEmailResult {
    private int totalCount;           // 전체 발송 시도 건수
    private int successCount;         // 성공 건수
    private int failCount;            // 실패 건수
    private List<String> failEmails;  // 실패한 이메일 목록
}