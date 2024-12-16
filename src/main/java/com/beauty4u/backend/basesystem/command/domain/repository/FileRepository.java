package com.beauty4u.backend.basesystem.command.domain.repository;

import com.beauty4u.backend.basesystem.command.domain.aggregate.FileInfo;
import com.beauty4u.backend.inform.command.domain.aggregate.Inform;
import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileInfo, Long> {

    void deleteByInform(Inform inform);

    void deleteByInquiry(Inquiry inquiry);

}
