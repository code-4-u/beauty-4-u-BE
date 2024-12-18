package com.beauty4u.backend.file.command.domain.aggregate;

import com.beauty4u.backend.inform.command.domain.aggregate.Inform;
import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
import com.beauty4u.backend.teamspace.command.domain.aggregate.TeamBoard;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "file_info")
public class FileInfo {

    @Id
    @Column(name = "file_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_board_id")
    private TeamBoard teamBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inform_id")
    private Inform inform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;
}