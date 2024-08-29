package social.media.network.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import social.media.network.entity.User.User;

public interface UserRepo extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.userEmail = :email")
    User findByUserEmail(@Param("email") String email);

    @Query("SELECT u From User u WHERE u.userId <> :id ORDER BY CASE " +
            "WHEN u.homeTown=:homeTown AND u.schoolName=:schoolName THEN 1" +
            "WHEN u.schoolName=:schoolName THEN 2" +
            "WHEN u.homeTown = :homeTown THEN 2 ELSE 3" +
            " END,u.userId DESC")
    Page<User> getSuggestFriend(
            @Param("id" ) Long id,
            @Param("homeTown") String homeTown,
            @Param("schoolName") String schoolName,
            Pageable pageable
    );

}
