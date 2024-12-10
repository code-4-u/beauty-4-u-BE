package com.beauty4u.backend.teamspace.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@Entity
@Table(name = "folder")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id", nullable = false)
    private Long folderId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamspace_id", nullable = false)
    private Teamspace teamspaceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_folder_id")
    private Folder topFolderId;

    @Size(max = 50)
    @NotNull
    @Column(name = "folder_name", nullable = false, length = 50)
    private String folderName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "publish_status", nullable = false)
    private FolderStatus folderStatus = FolderStatus.ACTIVE;

    @NotNull
    @Column(name = "folder_created_date", nullable = false)
    private ZonedDateTime folderCreatedDate;

    @Column(name = "folder_updated_date")
    private ZonedDateTime folderUpdatedDate;

    @Column(name = "folder_deleted_date")
    private ZonedDateTime folderDeletedDate;

}