package kr.co.littleriders.backend.global.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSender {

    private final JavaMailSender javaMailSender;
    private final String FROM;

    public MailSender(JavaMailSender javaMailSender, @Value("${spring.mail.from}") String FROM) {
        this.javaMailSender = javaMailSender;
        this.FROM = FROM;
    }

    public void sendVerificationEmail(String email, String code) {
        sendEmail(email, "리틀라이더즈 인증 코드 입니다.", code);
    }

    private void sendEmail(String email, String title, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            message.setFrom(FROM);
            message.setTo(email);
            message.setSubject(title);
            message.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException();
        }

    }
}
