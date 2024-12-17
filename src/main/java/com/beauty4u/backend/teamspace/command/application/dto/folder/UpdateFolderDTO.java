package com.beauty4u.backend.teamspace.command.application.dto.folder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFolderDTO {

    private Long topFolderId; // 상위 폴더 ID (루트 폴더일 경우 null)

    @NotNull
    @Size(max = 50)
    private String folderName; // 폴더 이름
}
