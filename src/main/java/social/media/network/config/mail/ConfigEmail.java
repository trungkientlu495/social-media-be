package social.media.network.config.mail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import java.util.Random;
@Configuration
@AllArgsConstructor
@Slf4j
public class ConfigEmail {
    // redisTemplace -> cache save otp register , redisForgotPassword --> save otp forgot password
    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String text,RedisTemplate<String, Integer> redisTemplate) {
        Integer a = new Random().nextInt(100000) + 1000;
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        sb.append(" ");
        sb.append(a);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(sb.toString());
        redisTemplate.opsForValue().set(to, a);
        log.info("OTP send email: "+a);
        mailSender.send(message);
    }
}
