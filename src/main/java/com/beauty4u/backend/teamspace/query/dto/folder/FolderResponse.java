package com.beauty4u.backend.teamspace.query.dto.folder;

import com.beauty4u.backend.teamspace.command.domain.aggregate.FolderStatus;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FolderResponse {

    private Long folderId;       // 폴더의 고유 ID
    private Long teamspaceId; // 팀스페이스 번호
    private Long topFolderId; // 상위 폴더 ID (없으면 null)
    private String folderName;   // 폴더 이름
    private FolderStatus folderStatus; // 폴더 상태
    private ZonedDateTime folderCreatedDate; // 폴더 생성 일시
    private ZonedDateTime folderUpdatedDate; // 폴더 수정 일시
    private ZonedDateTime folderDeletedDate; // 폴더 삭제 일시

}
