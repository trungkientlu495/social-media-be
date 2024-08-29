package social.media.network.payload.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class SentResponse {
    private String firstName;

    private String lastName;

    private String avatar;

    private Date sendRequest;

}
