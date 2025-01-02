package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.file.command.application.service.FileService;
import com.beauty4u.backend.teamspace.command.application.dto.chat.ChatMessageReqDto;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.command.domain.service.ChatMessageDomainService;
import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageDomainService chatMessageDomainService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final FileService fileService;
    private final ModelMapper modelMapper;


    public void sendMessage(Long chatRoomId, ChatMessageReqDto chatMessageReqDto) {

        // 파일 업로드
        List<String> fileS3Urls = new ArrayList<>();
        List<String> presignedUrls = new ArrayList<>();

        // s3에 이미 업로드를 마치고 s3 url들을 받은 상태에서의 로직이라고 생각해야함
        // 프론트에서 s3 url들을 모두 보내줘야함
        if(chatMessageReqDto.getFileS3Urls() != null){
            fileS3Urls.addAll(chatMessageReqDto.getFileS3Urls());
        }

        // S3 URL -> Presigned URL 변환
        for (String s3Url : fileS3Urls) {
            String objectKey = extractObjectKeyFromS3Url(s3Url); // S3 URL에서 객체 키 추출
            log.info("Extracted object key: {}", objectKey); // 디버깅 로그 추가
            presignedUrls.add(fileService.generatePresignedUrl(objectKey));
        }

        log.info("Extracted s3Url key: {}", fileS3Urls); // 디버깅 로그 추가
        log.info("Generated Presigned URL: {}", presignedUrls);

        // s3 url을 넣어줘야할 거 같은데
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .userCode(chatMessageReqDto.getUserCode())
                .userName(chatMessageReqDto.getUserName())
                .messageContent(chatMessageReqDto.getMessageContent())
                .messageCreatedTime(chatMessageReqDto.getMessageCreatedTime())
                .s3PresignedUrls(presignedUrls)
                .build();

        // 비동기로 메시지 저장
        saveMessageAsync(chatMessage);

        ChatMessageResDto chatMessageResDto =  modelMapper.map(chatMessage, ChatMessageResDto.class);

        // STOMP 메시지 전송
        // 해당 주소를 수신하고 있는 유저들에게 보내짐
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatRoomId, chatMessageResDto);
        log.info("Message [{}] saved and sent to destination [{}]", chatMessage.getMessageContent(), "/sub/chat/" + chatRoomId);
    }

    private String extractObjectKeyFromS3Url(String s3Url) {
        // S3 URL 예: https://bucket-name.s3.amazonaws.com/path/to/object/file.png
        try {
            URI uri = new URI(s3Url);
            String bucketDomain = uri.getHost(); // bucket-name.s3.amazonaws.com
            String path = uri.getPath(); // /path/to/object/file.png
            String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8); // URL 디코딩
            return path.startsWith("/") ? path.substring(1) : decodedPath; // 첫 '/' 제거
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid S3 URL: " + s3Url, e);
        }
    }

    @Async
    public void saveMessageAsync(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
        log.info("Message [{}] saved to database", chatMessage.getMessageContent());
    }

    public void deleteByChatRoomId(Long chatRoomId) {
        chatMessageDomainService.deleteByChatRoomId(chatRoomId);
    }
}
