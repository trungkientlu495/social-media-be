package social.media.network.services.Impl;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import social.media.network.config.async.AsyncConfig;
import social.media.network.services.CloudinaryServices;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServicesImpl implements CloudinaryServices {
    private final Cloudinary cloudinary;

    @Override
    public Map upload(MultipartFile file) {
        try{
            return cloudinary.uploader().upload(file.getBytes(), Map.of());
        }catch (IOException io){
            throw new RuntimeException("Image upload fail");
        }
    }


}
