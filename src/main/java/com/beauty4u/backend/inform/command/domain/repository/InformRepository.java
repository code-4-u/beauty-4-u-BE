package com.beauty4u.backend.inform.command.domain.repository;

import com.beauty4u.backend.inform.command.domain.aggregate.Inform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformRepository extends JpaRepository<Inform, Long> {
}
