package com.beauty4u.backend.schedule.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScheduleReqDTO {

    private String scheduleType;
    private String scheduleUrl;
    private ScheduleReqDTO scheduleReqDTO;
}
