package com.beauty4u.backend.schedule.command.domain.repository;

import com.beauty4u.backend.schedule.command.domain.aggregate.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleInfo, Long> {

    void deleteByScheduleUrl(String scheduleUrl);
}
