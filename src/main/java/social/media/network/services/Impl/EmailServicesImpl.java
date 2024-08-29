package social.media.network.services.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import social.media.network.config.mail.ConfigEmail;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServicesImpl implements social.media.network.services.EmailServices {
    // redisTemplace --> save otp register , redisForgotPassword --> save otp forgot password
    private final RedisTemplate<String, Integer> redisTemplate;
    private final RedisTemplate<String,Integer> redisForgotPassword;
    private final ConfigEmail configEmail;

    //send otp -> register account
    public void sendEmailRegister(String to, String subject, String text) {
        configEmail.sendEmail(to, subject, text,redisTemplate);
    }

    // send otp -> forgot password
    public void sendEmailForgot(String to, String subject, String text) {
        configEmail.sendEmail(to,subject,text,redisForgotPassword);
    }
}
