package com.beauty4u.backend.analysis.command.domain.repository;

import com.beauty4u.backend.analysis.command.domain.aggregate.Analysis;
import com.beauty4u.backend.analysis.command.domain.aggregate.AssociationRecommendation;
import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociationRecommendationRepository extends JpaRepository<AssociationRecommendation, Long> {

    List<AssociationRecommendation> findByGoodsCodeAndAnalysis(Goods goodsCode, Analysis analysis);
}
