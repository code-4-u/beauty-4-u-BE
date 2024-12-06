package com.beauty4u.backend.teamspace.command.domain.aggregate;

import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.Instant;
// mongodb를 사용할 예정임.

@Getter
@Entity
@Table(name = "last_read")
public class LastRead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "last_read_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamspace_id", nullable = false)
    private Teamspace teamspace;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @NotNull
    @Column(name = "last_entered_date", nullable = false)
    private Instant lastEnteredDate;

    @NotNull
    @Column(name = "last_exited_date", nullable = false)
    private Instant lastExitedDate;

}