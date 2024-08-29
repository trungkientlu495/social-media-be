package social.media.network.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryServices {
    Map upload(MultipartFile file);
}
