package com.beauty4u.backend.common.aggregate.entity;

import com.beauty4u.backend.common.aggregate.StatusType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity extends CreatedUpdatedTimeEntity {

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "publish_status", nullable = false)
    private StatusType publishStatus = StatusType.PUBLISHED;

    public void delete() {
        this.deletedDate = LocalDateTime.now();
        this.publishStatus = StatusType.DELETED;
    }

    public void publish() {
        this.deletedDate = null;
        this.publishStatus = StatusType.PUBLISHED;
    }
}
