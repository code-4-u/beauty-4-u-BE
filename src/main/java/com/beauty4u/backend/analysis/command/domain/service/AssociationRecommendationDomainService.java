package com.beauty4u.backend.analysis.command.domain.service;

import com.beauty4u.backend.analysis.command.application.dto.AprioriResDTO;
import com.beauty4u.backend.analysis.command.domain.aggregate.Analysis;
import com.beauty4u.backend.analysis.command.domain.aggregate.AssociationRecommendation;
import com.beauty4u.backend.analysis.command.domain.repository.AnalysisRepository;
import com.beauty4u.backend.analysis.command.domain.repository.AssociationRecommendationRepository;
import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import com.beauty4u.backend.goods.command.domain.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociationRecommendationDomainService {

    private final AssociationRecommendationRepository associationRecommendationRepository;
    private final AnalysisRepository analysisRepository;
    private final GoodsRepository goodsRepository;

    public List<AprioriResDTO> findAssociaionGoodsList(String goodsCode, Long analysisId) {
        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ANALYSIS));

        Goods goods = goodsRepository.findById(goodsCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_GOODS));

        List<AssociationRecommendation> byGoodsCodeAndAnalysis = associationRecommendationRepository.findByGoodsCodeAndAnalysis(goods, analysis);

        List<AprioriResDTO> result = new ArrayList<>();

        // 먼저 최대값들을 계산
        double maxSupport = byGoodsCodeAndAnalysis.stream()
                .mapToDouble(AssociationRecommendation::getSupport)
                .max()
                .orElse(1.0);

        double maxLift = byGoodsCodeAndAnalysis.stream()
                .mapToDouble(AssociationRecommendation::getLift)
                .max()
                .orElse(1.0);

        for (AssociationRecommendation ar : byGoodsCodeAndAnalysis) {
            AprioriResDTO aprioriResDTO = new AprioriResDTO();

            double support = ar.getSupport();
            double confidence = ar.getConfidence();
            double lift = ar.getLift();

            // 정규화 및 가중치 적용
            double normalizedSupport = support / maxSupport;
            double normalizedConfidence = confidence; // 이미 0~1 사이 값
            double normalizedLift = maxLift > 1.0 ? (lift - 1) / (maxLift - 1) : 0;

            // 가중치를 적용한 최종 점수 계산 (lift 40%, confidence 40%, support 20%)
            double score = (normalizedLift * 0.4) + (normalizedConfidence * 0.4) + (normalizedSupport * 0.2);

            aprioriResDTO.setGoodsCode(ar.getGoodsCode().getGoodsCode());
            aprioriResDTO.setGoodsName(ar.getGoodsCode().getGoodsName());
            aprioriResDTO.setAssociatedGoodsCode(ar.getAssociatedGoodsCode().getGoodsCode());
            aprioriResDTO.setAssociatedGoodsName(ar.getAssociatedGoodsCode().getGoodsName());
            aprioriResDTO.setSupport(support);
            aprioriResDTO.setConfidence(confidence);
            aprioriResDTO.setLift(lift);
            aprioriResDTO.setTotalScore(score);

            result.add(aprioriResDTO);
        }

        return result;
    }
}
