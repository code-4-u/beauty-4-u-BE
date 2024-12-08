package com.beauty4u.backend.basesystem.command.domain.aggregate;

import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
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
@Table(name = "noti")
public class Noti {
    @Id
    @Column(name = "noti_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamspace_id")
    private Teamspace teamspace;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "noti_content", nullable = false)
    private String notiContent;

    @NotNull
    @Column(name = "noti_read_yn", nullable = false)
    private Character notiReadYn;

    @NotNull
    @Column(name = "noti_created_time", nullable = false)
    private Instant notiCreatedTime;

}