package com.beauty4u.backend.teamspace.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_room")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id", nullable = false)
    private Long id;

    @Column(name = "chat_room_name", nullable = false, length = 50)
    private String chatRoomName;


}