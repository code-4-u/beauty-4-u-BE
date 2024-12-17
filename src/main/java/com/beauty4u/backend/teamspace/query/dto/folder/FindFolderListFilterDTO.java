package com.beauty4u.backend.teamspace.query.dto.folder;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindFolderListFilterDTO {

    private Long topFolderId;
    private String folderName;

    @NotNull(message = "팀 스페이스 입력은 필수입니다.")
    private Long teamspaceId;

    @NotNull(message = "폴더 상태는 입력은 필수입니다.")
    private String folderStatus;
}
