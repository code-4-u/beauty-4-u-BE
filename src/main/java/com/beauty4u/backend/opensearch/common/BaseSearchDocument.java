package com.beauty4u.backend.opensearch.common;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseSearchDocument {
    @Id
    protected String id;
    // 검색 문서 기본 틀
}
