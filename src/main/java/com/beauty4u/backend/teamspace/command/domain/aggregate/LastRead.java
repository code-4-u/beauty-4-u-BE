package com.beauty4u.backend.teamspace.command.domain.aggregate;

import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "last_read")
public class LastRead {
    @Id
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