package lewis.trenton.NoodlyUserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name="user-service")
public class NoodlyUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoodlyUserServiceApplication.class, args);
	}

}
