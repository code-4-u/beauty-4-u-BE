package com.beauty4u.backend.teamspace.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@Table(name = "folder")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE folder SET publish_status = 'DELETED', deleted_date = NOW() WHERE folder_id = ?")
public class Folder extends BaseEntity{

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

    public void modifyFolder(Folder topFolder, String folderName) {

        this.topFolderId = topFolder;
        this.folderName = folderName;
    }
}