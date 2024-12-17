package com.beauty4u.backend.teamspace.query.dto.folder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolderListResDTO {

    private Long folderId;
    private Long topFolderId;
    private Long teamspaceId;
    private String folderName;
}
