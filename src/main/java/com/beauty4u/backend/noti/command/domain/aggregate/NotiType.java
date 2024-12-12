package com.beauty4u.backend.noti.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotiType {
    TEAMSPACE("팀스페이스가 생성되었습니다."),
    INQUIRY("등록한 Q&A에 댓글이 달렸습니다.");

    private final String message;
}
