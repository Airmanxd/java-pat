package com.example.Practice22;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Async
    public void sendSimpleMessage(String subject, String text) {
        log.info("sending email to myself");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rek.va@mail.ru");
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        log.info("Setting mail sender");
        this.javaMailSender = javaMailSender;
    }
}
