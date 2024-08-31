package social.media.network.payload.dto.response;

import lombok.Data;
import social.media.network.entity.Post.EPostStatus;

import java.util.Date;
@Data
public class PostResponse {
    private String title;

    private String content;

    private String postStatus;

    private Date createdAt;
}
