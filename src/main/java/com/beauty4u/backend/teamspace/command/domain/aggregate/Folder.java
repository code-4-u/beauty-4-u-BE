package com.beauty4u.backend.teamspace.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "folder")
public class Folder extends BaseEntity {

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
}