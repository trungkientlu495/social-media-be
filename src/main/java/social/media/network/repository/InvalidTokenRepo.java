package social.media.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import social.media.network.entity.User.InvalidToken;

import java.util.Date;

public interface InvalidTokenRepo extends JpaRepository<InvalidToken, String> {
    @Transactional
    @Modifying
    @Query("DELETE FROM InvalidToken u WHERE u.expiryDate< :dateNow")
    void deleteToken(@Param("dateNow") Date dateNow);

}
