package com.beauty4u.backend.config.mail;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.username}")
    private String mailUserName;

    @Value("${spring.mail.password}")
    private String mailPassword;

    /* fakeSMTP 사용 mailSender */
    @Bean
    @Qualifier("fakeMailSender")
    public JavaMailSender fakeMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localHost");
        mailSender.setPort(25);
        mailSender.setProtocol("smtp");

        // 추가 속성 설정
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");     // 인증 불필요
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.debug", "true");     // 디버그 로그 활성화

        return mailSender;
    }

    /* Gmail SMTP 사용 mailSender */
    @Bean
    @Qualifier("gmailMailSender")
    public JavaMailSender gmailMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        // Gmail 계정 설정
        mailSender.setUsername(mailUserName);
        mailSender.setPassword(mailPassword);

        // 추가 속성 설정
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");     // 인증 불필요
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");     // 디버그 로그 활성화

        return mailSender;
    }

}
