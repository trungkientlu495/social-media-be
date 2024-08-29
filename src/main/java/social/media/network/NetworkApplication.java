package social.media.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class NetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkApplication.class, args);
	}

}
