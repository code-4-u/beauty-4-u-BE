package com.beauty4u.backend.schedule.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.promotion.command.domain.aggregate.Promotion;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedule_info")
public class ScheduleInfo extends BaseEntity {

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
    private LocalDateTime scheduleStart;

    @NotNull
    @Column(name = "schedule_end", nullable = false)
    private LocalDateTime scheduleEnd;
}