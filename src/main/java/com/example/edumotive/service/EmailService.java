package com.example.edumotive.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String toEmail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("clara.poghosyan@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Password Reset - EduMotive");
        String resetLink = "http://localhost:4200/reset-password?token=" + token;
        message.setText("Գաղտնաբառը վերականգնելու համար սեղմիր հետևյալ հղման վրա՝\n\n" + resetLink +
                "\n\nՀղումը գործում է 30 րոպե։\n\nԵթե դու այս հարցումը չես կատարել, անտեսիր այս նամակը։");
        mailSender.send(message);
    }
}