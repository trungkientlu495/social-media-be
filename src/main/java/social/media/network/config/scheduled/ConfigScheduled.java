package social.media.network.config.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import social.media.network.repository.InvalidTokenRepo;

import java.util.Date;

@EnableScheduling
@RequiredArgsConstructor
@Slf4j
@Configuration
public class ConfigScheduled {
    private final InvalidTokenRepo invalidTokenRepo;
    // len lich xoa token het han trong db invalidToken (token ma user da logout)
    // 1* --> phút 0 - 59
    // 2* --> giờ 0 - 23
    // 3* --> ngày trong tháng
    // 4* --> tháng
    // 5* --> ngày trong tuần 0 - 6
    @Scheduled(cron = "* 0 * * * * ")
    public void scheduled() {
        log.info("scheduled start");
        invalidTokenRepo.deleteToken(new Date());
    }

}
