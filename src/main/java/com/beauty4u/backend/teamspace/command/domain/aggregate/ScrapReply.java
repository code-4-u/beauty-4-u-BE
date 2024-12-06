package com.beauty4u.backend.teamspace.command.domain.aggregate;

import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "scrap_reply")
public class ScrapReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_reply_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scrap_id", nullable = false)
    private Scrap scrap;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @Lob
    @Column(name = "scrap_reply_content")
    private String scrapReplyContent;

    @NotNull
    @Lob
    @Column(name = "scrap_reply_status", nullable = false)
    private String scrapReplyStatus;

    @NotNull
    @Column(name = "scrap_reply_created_date", nullable = false)
    private Instant scrapReplyCreatedDate;

    @Column(name = "scrap_reply_updated_date")
    private Instant scrapReplyUpdatedDate;

    @Column(name = "scrap_reply_deleted_date")
    private Instant scrapReplyDeletedDate;

}