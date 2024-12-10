package com.beauty4u.backend.marketing.command.domain.repository;

import com.beauty4u.backend.marketing.command.domain.aggregate.MarketingSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketingSettingRepository extends JpaRepository<MarketingSetting, Long> {
}
