package com.beauty4u.backend.goods.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeminiRequestDTO {
    private List<Content> contents;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        private List<Part> parts;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Part {
        private String text;
    }

    // 편의 메서드: 텍스트로 요청 객체 생성
    public static GeminiRequestDTO of(String text) {
        return GeminiRequestDTO.builder()
                .contents(List.of(
                        Content.builder()
                                .parts(List.of(
                                        Part.builder()
                                                .text(text)
                                                .build()
                                ))
                                .build()
                ))
                .build();
    }
}
