package com.example.Practice24.serviceTests;

import com.example.Practice24.repositories.DepartureRepository;
import com.example.Practice24.repositories.PostOfficeRepository;
import com.example.Practice24.repositories.UserRepository;
import com.example.Practice24.services.DepartureService;
import com.example.Practice24.services.EmailService;
import com.example.Practice24.services.PostOfficeService;
import com.example.Practice24.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.mail.MailHealthContributorAutoConfiguration;
import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.boot.actuate.mail.MailHealthIndicator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MailingTest {
    @MockBean
    private UserRepository userRepository;
    private UserService userService;
    @MockBean
    private PostOfficeRepository postOfficeRepository;
    private PostOfficeService postOfficeService;
    @MockBean
    private DepartureRepository departureRepository;
    private DepartureService departureService;
    @Autowired
    private EmailService emailService;
    @MockBean
    MailHealthContributorAutoConfiguration NICE;
    @MockBean
    HealthContributorRegistry AUTOCREATION;
    @MockBean
    private JavaMailSender sender;

    @Captor
    ArgumentCaptor<SimpleMailMessage> captor;

    private CountDownLatch lock = new CountDownLatch(1);


    @Test
    void sendEmail() throws InterruptedException {

        emailService.sendSimpleMessage("Subject", "Body");
        Mockito.verify(sender, times(1)).send(captor.capture());
        Assertions.assertEquals("Subject", captor.getValue().getSubject());
        Assertions.assertEquals("Body", captor.getValue().getText());

    }
}
