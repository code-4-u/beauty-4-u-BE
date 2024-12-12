package com.beauty4u.backend.noti.command.domain.repository;

import com.beauty4u.backend.noti.command.domain.aggregate.Noti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotiRepository extends JpaRepository<Noti, Long> {
}
