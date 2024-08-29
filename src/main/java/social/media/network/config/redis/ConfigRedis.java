package social.media.network.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ConfigRedis {
    @Value("localhost")
    private String redisHost;

    @Value("6379")
    private int redisPort;


    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // Tạo Standalone Connection tới Redis
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }

    @Bean
    @Primary
    public RedisTemplate<String, Integer> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // tạo ra một RedisTemplate
        // Với Key là Object
        // Value là Object
        // RedisTemplate giúp chúng ta thao tác với Redis
        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    // request register <--> register otp ( luu registerresponse )
    @Bean
    public RedisTemplate<String, Object> saveRegister(RedisConnectionFactory redisConnectionFactory) {
        // tạo ra một RedisTemplate
        // Với Key là Object
        // Value là Object
        // RedisTemplate giúp chúng ta thao tác với Redis
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


}
