package social.media.network.payload.dto.request;

import jakarta.persistence.*;
import social.media.network.entity.Post.EPostStatus;
import social.media.network.entity.User.User;

import java.util.Date;

public class PostRequest {
    private String title;

    private String content;

    private String ePostStatus;

}
