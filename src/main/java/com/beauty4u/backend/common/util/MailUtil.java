package com.beauty4u.backend.common.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MailUtil {

    @Value("${spring.mail.username}")
    private String mailUserName;

    private final JavaMailSender fakeMailSender;
    private final JavaMailSender gmailMailSender;

    public MailUtil(
            @Qualifier("fakeMailSender") JavaMailSender fakeMailSender,
            @Qualifier("gmailMailSender") JavaMailSender gmailMailSender
    ) {
        this.fakeMailSender = fakeMailSender;
        this.gmailMailSender = gmailMailSender;
    }

    /* fakeSMTP 사용 - 테스트 시 사용 */
    @Async
    public CompletableFuture<Void> sendPromotionFakeSmtpAsync(String email, String title, String body) {
        return CompletableFuture.runAsync(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(title);
            message.setText(body);
            fakeMailSender.send(message);
        });
    }

    /* Gmail 사용 - 시현시 사용 3개의 이메일 주소로 발송 */
    @Async
    public CompletableFuture<Void> sendPromotionGmailAsync(String email, String title, String body) {
        return CompletableFuture.runAsync(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(title);
            message.setText(body);
            message.setFrom(mailUserName);
            gmailMailSender.send(message);
        });
    }

    /* 설빈 구축 메소드 Gmail 사용변경 */
    public void sendEmail(String email, String title, String body) {

        /* 이메일 메시지 생성 */
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email); /* 수신자 이메일 */
        message.setSubject(title); /* 이메일 제목 */
        message.setText(body); /* 이메일 본문 */
        message.setFrom(mailUserName);

        /* 이메일 전송 */
        gmailMailSender.send(message);
    }
}
