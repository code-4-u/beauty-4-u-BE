package com.beauty4u.backend.basesystem.command.domain.aggregate;

import com.beauty4u.backend.promotion.command.domain.aggregate.Promotion;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "schedule_info")
public class ScheduleInfo {
    @Id
    @Column(name = "schedule_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamspace_id")
    private Teamspace teamspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "schedule_title", nullable = false, length = 50)
    private String scheduleTitle;

    @Size(max = 255)
    @Column(name = "schedule_content")
    private String scheduleContent;

    @NotNull
    @Column(name = "schedule_start", nullable = false)
    private Instant scheduleStart;

    @NotNull
    @Column(name = "schedule_end", nullable = false)
    private Instant scheduleEnd;

    @NotNull
    @Lob
    @Column(name = "schedule_status", nullable = false)
    private String scheduleStatus;

    @NotNull
    @Column(name = "schedule_created_date", nullable = false)
    private Instant scheduleCreatedDate;

    @Column(name = "schedule_updated_date")
    private Instant scheduleUpdatedDate;

    @Column(name = "schedule_deleted_date")
    private Instant scheduleDeletedDate;

}