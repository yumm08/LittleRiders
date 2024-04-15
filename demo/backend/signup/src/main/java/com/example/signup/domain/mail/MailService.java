package com.example.signup.domain.mail;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    private final String FROM;

    public MailService(JavaMailSender javaMailSender,@Value("${spring.mail.from}") String FROM) {
        this.javaMailSender = javaMailSender;
        this.FROM = FROM;
    }

    @Async
    public void sendEmail(String email,String subject, String content){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            message.setFrom(FROM);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException();
        }
    }
}
