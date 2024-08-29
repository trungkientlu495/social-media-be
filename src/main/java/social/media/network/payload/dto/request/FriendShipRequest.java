package social.media.network.payload.dto.request;

import lombok.Data;
import social.media.network.payload.paging.Page;

@Data
public class FriendShipRequest {
    private Long idUser;
    private Page page;
}
