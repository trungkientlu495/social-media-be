package social.media.network.entity.User;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class InvalidToken {
    @Id
    private String token;
    private Date expiryDate;
}
