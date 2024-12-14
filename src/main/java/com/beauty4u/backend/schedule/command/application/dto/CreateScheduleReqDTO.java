package com.beauty4u.backend.schedule.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateScheduleReqDTO {

    private String scheduleTitle;
    private String scheduleContent;
    private String scheduleType;
    private String scheduleUrl;
    private LocalDateTime scheduleStart;
    private LocalDateTime scheduleEnd;
}
