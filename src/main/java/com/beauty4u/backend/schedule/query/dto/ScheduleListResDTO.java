package com.beauty4u.backend.schedule.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleListResDTO {

    private Long scheduleId;
    private String scheduleTitle;
    private String scheduleContent;
    private String scheduleType;
    private String scheduleUrl;
    private String scheduleStart;
    private String scheduleEnd;
}
