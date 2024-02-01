package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String from;

    @Value("${mail.send}")
    private int sendMail;

    @Value("${mail.text}")
    private String createdText;

    @Value("${mail.subject}")
    private String subject;

    @Override
    public void sendMail(String subject, String to, String text) {
        if (sendMail == 1) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
        }
    }

    @Override
    public void sendBulkmail(List<String> to) {
        try {
            for (String email : to) {
                sendMail(subject, email, createdText);
                log.info("Email has been sent to agents about new request: " + email);
            }
        } catch (Exception exp) {
            log.error("Couldn't send mail for request :" + "\r\n to : " + to, exp);
        }
    }
}
