package com.beauty4u.backend.analysis.command.application.service;

import com.beauty4u.backend.analysis.command.application.dto.CollaboFilterResDTO;
import com.beauty4u.backend.analysis.command.infrastructure.flaskApi.CollaboFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollaboFilterService {

    private final CollaboFilter collaboFilter;

    public CollaboFilterResDTO runCollaboFilter() {
        return collaboFilter.runCollaboFilter();
    }

}
