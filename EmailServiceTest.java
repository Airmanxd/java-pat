package com.example.Practice24;

import com.example.Practice24.services.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmailServiceTest {
    @Autowired
    private EmailService service;

    @MockBean
    private JavaMailSender sender;

    @Captor
    ArgumentCaptor<SimpleMailMessage> captor;
    @Test
    public void sendTest()
    {
        service.sendSimpleMessage("Test", "hiiiiiiiiiiii");
        verify(sender).send(ArgumentMatchers.any(SimpleMailMessage.class));
        verify(sender).send(captor.capture());
        assertEquals(captor.getValue().getSubject(), "Test");
        assertEquals(captor.getValue().getText(), "hiiiiiiiiiiii");
    }

}
