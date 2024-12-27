package com.beauty4u.backend.analysis.command.infrastructure.flaskApi;

import com.beauty4u.backend.analysis.command.application.dto.CollaboFilterResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "collaboFilter", url = "${apriori.api.url}")
public interface CollaboFilter {
    @PostMapping("/collaboFilter")
    CollaboFilterResDTO runCollaboFilter();
}
