package com.beauty4u.backend.schedule.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
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
    @Column(name = "schedule_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    @Size(max = 50)
    @NotNull
    @Column(name = "schedule_url", nullable = false)
    private String scheduleUrl;

    @NotNull
    @Column(name = "schedule_start", nullable = false)
    private LocalDateTime scheduleStart;

    @NotNull
    @Column(name = "schedule_end", nullable = false)
    private LocalDateTime scheduleEnd;

    public void modifyUser(UserInfo user) {
        this.userCode = user;
    }
}