package lewis.trenton.NoodlyEmailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name="email-service")
public class NoodlyEmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoodlyEmailServiceApplication.class, args);
	}

}
