package com.beauty4u.backend.teamspace.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamspace_id", nullable = false)
    private Teamspace teamspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_folder_id")
    private Folder topFolder;

    @Size(max = 50)
    @NotNull
    @Column(name = "folder_name", nullable = false, length = 50)
    private String folderName;

    @NotNull
    @Lob
    @Column(name = "folder_status", nullable = false)
    private String folderStatus;

    @NotNull
    @Column(name = "folder_created_date", nullable = false)
    private Instant folderCreatedDate;

    @Column(name = "folder_updated_date")
    private Instant folderUpdatedDate;

    @Column(name = "folder_deleted_date")
    private Instant folderDeletedDate;

}