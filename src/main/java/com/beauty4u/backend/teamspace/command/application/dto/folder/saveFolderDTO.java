package com.beauty4u.backend.teamspace.command.application.dto.folder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class saveFolderDTO {

    @NotNull
    private Long teamspaceId; // 소속 팀스페이스 ID

    private Long topFolderId; // 상위 폴더 ID (루트 폴더일 경우 null)

    @NotNull
    @Size(max = 50)
    private String folderName; // 폴더 이름
}
