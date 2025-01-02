package com.beauty4u.backend.noti.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotiType {
    TEAMSCHEDULE("팀 일정을 등록하였습니다."),
    INQUIRY("등록한 Q&A에 댓글이 달렸습니다."),
    PROMOTION("일정이 등록되었습니다."),
    CHAT("채팅이 왔습니다.");

    private final String message;
}
