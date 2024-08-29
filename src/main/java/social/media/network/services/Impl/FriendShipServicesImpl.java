package social.media.network.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import social.media.network.entity.FriendShip.EFriendshipStatus;
import social.media.network.entity.FriendShip.FriendShip;
import social.media.network.entity.User.User;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.response.FriendResponse;
import social.media.network.payload.mapping.FriendMapper;
import social.media.network.repository.FriendShipRepo;
import social.media.network.repository.UserRepo;
import social.media.network.services.FriendShipServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendShipServicesImpl implements FriendShipServices {
    private final AuthServicesImpl authServices;
    private final UserRepo userRepo;
    private final FriendShipRepo friendShipRepo;

    @Override
    public MessageResponse sendRequestFriend(Long idRecevived) {
        User sendUser = authServices.getAuth();
        User receviedUser = userRepo.findById(idRecevived).orElseThrow(
                () -> new CustormException("User does not exist")
        );
        if (sendUser.getUserId().equals(receviedUser.getUserId())) {
            throw new CustormException("Can't send request to self");
        }
        authServices.checkDelete(receviedUser);
        authServices.checkBlock(sendUser, receviedUser);
        FriendShip friendShip1 = friendShipRepo.findFriendShipBy(sendUser.getUserId(), idRecevived);
        FriendShip friendShip2 = friendShipRepo.findFriendShipBy(idRecevived, sendUser.getUserId());
        if (friendShip1 == null && friendShip2 == null) {
            FriendShip friendShip = new FriendShip();
            friendShip.setUserSendRequest(sendUser);
            friendShip.setUserReceivedRequest(receviedUser);
            friendShip.setCreateAt(new Date());
            friendShipRepo.save(friendShip);
            return new MessageResponse("User send add friend successfully");
        }
        return new MessageResponse("Before you sent a friend request to this user");
    }

    @Override
    public MessageResponse removeReceivedRequestFriend(Long idSend) {
        User userReceived = authServices.getAuth();
        User userSend = userRepo.findById(idSend).orElseThrow(
                () -> new CustormException("User does not exist")
        );
        if (userReceived.getUserId().equals(idSend)) {
            throw new CustormException("Input user invalid");
        }
        authServices.checkDelete(userSend);
        authServices.checkBlock(userReceived, userSend);
        FriendShip friendShip1 = friendShipRepo.findFriendShipBy(idSend, userReceived.getUserId());
        if (friendShip1 != null && friendShip1.getEFriendshipStatus().equals(EFriendshipStatus.PENDING)) {
                friendShipRepo.delete(friendShip1);
                return new MessageResponse("User removed received request friendship successfully");
        }
        return new MessageResponse("Dont find Request");
    }

    @Override
    public MessageResponse removeSentRequestFriend(Long idReceived) {
        User userSend = authServices.getAuth();
        User userReceived = userRepo.findById(idReceived).orElseThrow(
                () -> new CustormException("User does not exist")
        );
        if (userSend.getUserId().equals(idReceived)) {
            throw new CustormException("Input user invalid");
        }
        authServices.checkDelete(userReceived);
        authServices.checkBlock(userSend, userReceived);
        //find user sent request friend -> UserReceived (Yes/No)
        FriendShip friendShip1 = friendShipRepo.findFriendShipBy(userSend.getUserId(), idReceived);
        if (friendShip1 != null && friendShip1.getEFriendshipStatus().equals(EFriendshipStatus.PENDING)) {
            // neu k la ban be thi xoa yeu cau
                friendShipRepo.delete(friendShip1);
                return new MessageResponse("User removed sent request friendship successfully");
        }
        return new MessageResponse("Dont find Request");
    }

    @Override
    public MessageResponse acceptRequestFriend(Long idSend) {
        User receviedUser = authServices.getAuth();
        User sendUser = userRepo.findById(idSend).orElseThrow(
                () -> new CustormException("User does not exist")
        );
        if (receviedUser.getUserId().equals(idSend)) {
            throw new CustormException("User can't accept request friend self");
        }
        authServices.checkDelete(sendUser);
        authServices.checkBlock(sendUser, receviedUser);
        // find user received user sent request friend (yes/no)
        FriendShip friendShip = friendShipRepo.findFriendShipBy(idSend, receviedUser.getUserId());
        // friendShip co yeu cau ket ban ma usersend gui toi k va ho da la ban be chua --> PENDING
        if (friendShip != null && friendShip.getEFriendshipStatus().equals(EFriendshipStatus.PENDING)) {
            friendShip.setEFriendshipStatus(EFriendshipStatus.FRIEND);
            friendShipRepo.save(friendShip);
            return new MessageResponse("Accept request friend success");
        }
        return new MessageResponse("Request friend does not exist");
    }

    @Override
    public MessageResponse unFriendShip(Long id) {
        User user1 = authServices.getAuth();
        User user2 = userRepo.findById(id).orElseThrow(
                () -> new CustormException("User does not exist")
        );
        if (user1.getUserId().equals(id)) {
            throw new CustormException("User can't un friend your self");
        }
        authServices.checkDelete(user2);
        authServices.checkBlock(user1, user2);
        FriendShip friendShip1 = friendShipRepo.findFriendShipBy(user1.getUserId(), id);
        FriendShip friendShip2 = friendShipRepo.findFriendShipBy(id, user1.getUserId());
        // CHECK user1 sent request friend user2 ->> !null -> co gui va check status friend !PENDING --> OK
        if (friendShip1 != null && (!friendShip1.getEFriendshipStatus().equals(EFriendshipStatus.PENDING))) {
            friendShipRepo.delete(friendShip1);
            return new MessageResponse("Delete Friendship success");
        }
        // CHECK user2 sent request friend user1 ->> !null -> co gui va check status friend !PENDING --> OK
        if (friendShip2 != null && (!friendShip2.getEFriendshipStatus().equals(EFriendshipStatus.PENDING))) {
            friendShipRepo.delete(friendShip2);
            return new MessageResponse("Delete Friendship success");
        }
        return new MessageResponse("You two are not friends");
    }

    // xem list : check user xem list friend la user chinh chu
    // hay la ban xem friend cua user do
    @Override
    public Page<FriendResponse> listFriendShip(Long id, Pageable pageable) {
        User user1 = authServices.getAuth();
        User user2 = userRepo.findById(id).orElseThrow(
                () -> new CustormException("User does not exist")
        );
        //check user1 != user2 -->> checkBlock
        if (!user1.getUserId().equals(id)) {
            authServices.checkBlock(user1, user2);
        }
        authServices.checkDelete(user2);
        //check list da received request friend ma usser2 da gui
        Page<User> users1 = friendShipRepo.listSentRequest(EFriendshipStatus.PENDING, id, pageable);
        //check list da sent request friend den user2
        Page<User> users2 = friendShipRepo.listReceivedRequest(EFriendshipStatus.PENDING, id, pageable);
        List<FriendResponse> friendResponses;
        List<User> userResponses = new ArrayList<>();
        userResponses.addAll(users1.getContent());
        userResponses.addAll(users2.getContent());
        friendResponses = FriendMapper.INSTANCE.toUsers(userResponses);
        return new PageImpl<>(friendResponses, pageable, friendResponses.size());
    }

    @Override
    public Page<FriendResponse> listSentRequestFriend(Pageable pageable) {
        User user1 = authServices.getAuth();
        Page<User> users = friendShipRepo.listSentRequest(EFriendshipStatus.PENDING, user1.getUserId(), pageable);
        List<FriendResponse> friendResponses;
        friendResponses = FriendMapper.INSTANCE.toUsers(users.getContent());
        return new PageImpl<>(friendResponses, pageable, friendResponses.size());
    }

    @Override
    public Page<FriendResponse> listReceivedRequestFriend(Pageable pageable) {
        User user1 = authServices.getAuth();
        Page<User> users = friendShipRepo.listReceivedRequest(EFriendshipStatus.PENDING, user1.getUserId(), pageable);
        List<FriendResponse> friendResponses;
        friendResponses = FriendMapper.INSTANCE.toUsers(users.getContent());
        return new PageImpl<>(friendResponses, pageable, friendResponses.size());
    }

    @Override
    public Page<FriendResponse> listSuggestFriend(Pageable pageable) {
        return null;
    }

    @Override
    public Page<User> listSuggestFrienda(Pageable pageable) {
        User user = authServices.getAuth();
        return userRepo.getSuggestFriend(user.getUserId(), user.getHomeTown(),user.getSchoolName(),pageable);
    }
}
