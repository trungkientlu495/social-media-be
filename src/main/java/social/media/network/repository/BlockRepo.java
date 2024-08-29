package social.media.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import social.media.network.entity.User.Block;

import java.util.List;

public interface BlockRepo extends JpaRepository<Block, Integer> {
    @Query("SELECT u FROM Block u WHERE u.blockedUser.userId = :idBlockUser")
    List<Block> findByBlockedUser(@Param("idBlockUser") Long idBlockUser);

}
