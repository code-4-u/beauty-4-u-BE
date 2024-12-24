package com.beauty4u.backend.file.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "file_info")
public class FileInfo {

    @Id
    @Column(name = "file_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "file_s3_url", nullable = false)
    private String fileS3Url;

    @NotNull
    @Column(name = "file_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Size(max = 50)
    @NotNull
    @Column(name = "file_url", nullable = false)
    private String fileUrl;
}