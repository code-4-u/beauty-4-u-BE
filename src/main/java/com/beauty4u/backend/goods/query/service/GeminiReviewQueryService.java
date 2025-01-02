package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import com.beauty4u.backend.goods.command.domain.aggregate.Review;
import com.beauty4u.backend.goods.command.domain.repository.GoodsRepository;
import com.beauty4u.backend.goods.command.domain.repository.ReviewRepository;
import com.beauty4u.backend.goods.query.dto.GeminiRequestDTO;
import com.beauty4u.backend.goods.query.dto.GeminiResponse;
import com.beauty4u.backend.goods.query.dto.GeminiReviewResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiReviewQueryService {

    @Qualifier("geminiRestTemplate")
    private final RestTemplate restTemplate;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final GoodsRepository goodsRepository;
    private final ReviewRepository reviewRepository;

    public String getContents(String prompt) {

        // Gemini에 요청 전송
        String requestUrl = apiUrl + "?key=" + geminiApiKey;

        GeminiRequestDTO request = GeminiRequestDTO.of(prompt);
        GeminiResponse response = restTemplate.postForObject(requestUrl, request, GeminiResponse.class);

        String message = response.getCandidates().get(0).getContent().getParts().get(0).getText();

        return message;
    }

    public GeminiReviewResDTO getGeminiReviewSummary(String goodsCode) {

        GeminiReviewResDTO geminiReviewResDTO = new GeminiReviewResDTO();

        Goods goods = goodsRepository.findById(goodsCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_GOODS));

        geminiReviewResDTO.setGoodsCode(goodsCode);
        geminiReviewResDTO.setGoodsName(goods.getGoodsName());

        List<Review> reviewList = reviewRepository.findByGoodsCode(goods);

        StringBuilder sb = new StringBuilder();

        for (Review review : reviewList) {
            sb.append(review.getReviewContent()).append("\n");
        }

        sb.append("한국말로 50자 내외로 요약해줘.");

        String geminiReview = getContents(sb.toString());

        geminiReviewResDTO.setGeminiReview(geminiReview);

        return geminiReviewResDTO;
    }
}
