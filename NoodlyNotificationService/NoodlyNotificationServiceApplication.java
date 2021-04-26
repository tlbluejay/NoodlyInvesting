package lewis.trenton.NoodlyNotificationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name="notification-service")
public class NoodlyNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoodlyNotificationServiceApplication.class, args);
	}

}
