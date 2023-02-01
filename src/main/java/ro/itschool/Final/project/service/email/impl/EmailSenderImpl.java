package ro.itschool.Final.project.service.email.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ro.itschool.Final.project.service.email.EmailSender;

@Service
@Slf4j
public class EmailSenderImpl implements EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("conttesttest4@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        EmailSenderImpl.log.info("Email Sent Successfully");
    }
}