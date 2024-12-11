package com.beauty4u.backend.noti.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.YnType;
import com.beauty4u.backend.common.aggregate.entity.CreatedTimeEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "noti")
@NoArgsConstructor
public class Noti extends CreatedTimeEntity {

    @Id
    @Column(name = "noti_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "noti_receiver", nullable = false)
    private UserInfo notiReceiver;

    @NotNull
    @Column(name = "noti_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotiType notiType;

    @Size(max = 255)
    @NotNull
    @Column(name = "noti_content", nullable = false)
    private String notiContent;

    @Size(max = 50)
    @NotNull
    @Column(name = "noti_url", nullable = false)
    private String notiUrl;

    @NotNull
    @Column(name = "noti_read_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private YnType notiReadYn;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "noti_sender", nullable = false)
    private UserInfo notiSender;

    @Builder
    public Noti(Long id, UserInfo notiReceiver, NotiType notiType, String notiContent,
                String notiUrl, YnType notiReadYn, UserInfo notiSender) {
        this.id = id;
        this.notiReceiver = notiReceiver;
        this.notiType = notiType;
        this.notiContent = notiContent;
        this.notiUrl = notiUrl;
        this.notiReadYn = notiReadYn;
        this.notiSender = notiSender;
    }
}