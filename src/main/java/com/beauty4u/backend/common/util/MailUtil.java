package com.beauty4u.backend.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailUtil {

    private final JavaMailSender mailSender;

    public void sendEmail(String email, String title, String body) {

        /* 이메일 메시지 생성 */
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email); /* 수신자 이메일 */
        message.setSubject(title); /* 이메일 제목 */
        message.setText(body); /* 이메일 본문 */

        /* 이메일 전송 */
        mailSender.send(message);
    }
}
