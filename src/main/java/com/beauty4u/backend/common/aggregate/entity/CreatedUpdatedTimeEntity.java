package com.beauty4u.backend.common.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreatedUpdatedTimeEntity extends CreatedTimeEntity {

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
