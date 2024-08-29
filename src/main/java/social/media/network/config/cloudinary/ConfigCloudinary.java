package social.media.network.config.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigCloudinary {
    @Value("dasnxk41i")
    private String cloudName;
    @Value("114814399859492")
    private String apiKey;
    @Value("Vq2JlQ6kHfpHlwO2z4RXc0N6fNE")
    private String apiSecret;
    @Bean
    public Cloudinary cloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
