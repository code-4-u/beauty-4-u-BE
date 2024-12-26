package com.beauty4u.backend.analysis.command.infrastructure.flaskApi;

import com.beauty4u.backend.analysis.command.application.dto.AprioriRequest;
import com.beauty4u.backend.analysis.command.application.dto.AprioriResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// AprioriClient.java
@FeignClient(name = "apriori", url = "${apriori.api.url}")
public interface AprioriClient {
    @PostMapping("/apriori")
    ResponseEntity<AprioriResponse> analyzeProducts(@RequestBody AprioriRequest request);
}
