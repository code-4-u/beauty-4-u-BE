package com.beauty4u.backend.schedule.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScheduleType {
    TEAMSCHEDULE("팀 일정이 등록되었습니다."),
    PROMOTION("프로모션 일정이 등록되었습니다.");

    private final String message;
}
