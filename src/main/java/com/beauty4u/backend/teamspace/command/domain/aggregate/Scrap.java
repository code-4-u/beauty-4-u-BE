package com.beauty4u.backend.teamspace.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import java.time.ZonedDateTime;

@Getter
@Entity
@Table(name = "scrap")
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id", nullable = false)
    private Long scrapId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_code", nullable = false)
    private UserInfo userCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "scrap_name", nullable = false, length = 50)
    private String scrapName;

    @Size(max = 255)
    @Column(name = "scrap_content")
    private String scrapContent;

    @Size(max = 50)
    @NotNull
    @Column(name = "file_url", nullable = false, length = 50)
    private String fileUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "publish_status", nullable = false)
    private ScrapStatus scrapStatus = ScrapStatus.ACTIVE;

    @NotNull
    @Column(name = "scrap_created_date", nullable = false)
    private ZonedDateTime scrapCreatedDate;

    @Column(name = "scrap_updated_date")
    private ZonedDateTime scrapUpdatedDate;

    @Column(name = "scrap_deleted_date")
    private ZonedDateTime scrapDeletedDate;
}