package social.media.network.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import social.media.network.entity.FriendShip.FriendShip;
import social.media.network.entity.User.User;
import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.response.FriendResponse;

import java.util.List;

public interface FriendShipServices {
    MessageResponse sendRequestFriend(Long idRecevived);

    MessageResponse removeReceivedRequestFriend(Long id);

    MessageResponse removeSentRequestFriend(Long id);

    MessageResponse acceptRequestFriend(Long id);

    MessageResponse unFriendShip(Long id);

    Page<FriendResponse> listFriendShip(Long id,Pageable pageable);

    Page<FriendResponse> listSentRequestFriend(Pageable pageable);

    Page<FriendResponse> listReceivedRequestFriend(Pageable pageable);

    Page<FriendResponse> listSuggestFriend(Pageable pageable);

    Page<User> listSuggestFrienda(Pageable pageable);
}
