package social.media.network.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import social.media.network.entity.FriendShip.FriendShip;
import social.media.network.entity.User.User;

import java.util.List;

public interface FriendShipRepo extends JpaRepository<FriendShip, Long> {
    //check 2 user da tung gui ket ban chua
    //check user co gui yeu cau den user nay k
    //check .... nhan .....
    @Query("SELECT u FROM FriendShip u WHERE u.userSendRequest.userId = :idSend " +
            "AND u.userReceivedRequest.userId = :idReceived")
    FriendShip findFriendShipBy(@Param("idSend") Long idSend,@Param("idReceived") Long idReceived);

    //list user ma user da gui yeu cau ket ban
    @Query("SELECT u.userReceivedRequest FROM FriendShip u WHERE u.eFriendshipStatus NOT IN :x " +
            "AND u.userSendRequest.userId = :idUser")
    Page<User> listSentRequest(@Param("x") Enum x, @Param("idUser") Long idUser, Pageable pageable);

    //list yeu cau ket ban ma user khac gui toi
    @Query("SELECT u.userSendRequest FROM FriendShip u WHERE u.eFriendshipStatus NOT IN :x " +
            "AND u.userReceivedRequest.userId = :idUser")
    Page<User> listReceivedRequest(@Param("x") Enum x, @Param("idUser") Long idUser, Pageable pageable);


    //list friend
    @Query("SELECT u.userReceivedRequest FROM FriendShip u WHERE u.eFriendshipStatus IN :x " +
            "AND u.userSendRequest.userId = :idUser")
    Page<User> findFriendForUserSend2(@Param("x") Enum x, @Param("idUser") Long idUser, Pageable pageable);

}
