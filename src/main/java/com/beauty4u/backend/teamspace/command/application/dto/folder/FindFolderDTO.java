package com.beauty4u.backend.teamspace.command.application.dto.folder;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Folder;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindFolderDTO extends BaseEntity {

    private Long folderId;
    private Teamspace teamspaceId;
    private Folder topFolderId;
    private String folderName;
}
