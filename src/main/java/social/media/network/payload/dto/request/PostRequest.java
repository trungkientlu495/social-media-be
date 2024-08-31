package social.media.network.payload.dto.request;

import lombok.Data;

@Data
public class PostRequest {

    private Integer id;

    private String title;

    private String content;

    private String postStatus;


}
