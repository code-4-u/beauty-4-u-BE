package com.beauty4u.backend.analysis.command.application.service;

import com.beauty4u.backend.analysis.command.application.dto.AprioriRequest;
import com.beauty4u.backend.analysis.command.application.dto.AprioriResDTO;
import com.beauty4u.backend.analysis.command.application.dto.AprioriResponse;
import com.beauty4u.backend.analysis.command.domain.service.AssociationRecommendationDomainService;
import com.beauty4u.backend.analysis.command.infrastructure.flaskApi.AprioriClient;
import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AprioriService {

    private final AprioriClient aprioriClient;
    private final AssociationRecommendationDomainService associationRecommendationDomainService;

    public List<AprioriResDTO> analysisApriori(AprioriRequest aprioriRequest) {

        ResponseEntity<AprioriResponse> aprioriResult = aprioriClient.analyzeProducts(aprioriRequest);

        if (!aprioriResult.getStatusCode().is2xxSuccessful()) {
            throw new CustomException(ErrorCode.APRIORI_FAIL);
        }

        List<AprioriResDTO> aprioriResDTOS = null;

        try {

            aprioriResDTOS
                    = associationRecommendationDomainService
                    .findAssociaionGoodsList(aprioriRequest.getGoodsCode(), aprioriResult.getBody().getAnalysisId());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_FOUND_ASSOCIATION_RECOMMENDATION);
        }

        return aprioriResDTOS;
    }
}
