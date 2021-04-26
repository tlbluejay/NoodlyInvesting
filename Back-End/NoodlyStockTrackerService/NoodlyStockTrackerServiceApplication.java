package lewis.trenton.NoodlyStockTrackerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name="tracker-service")
public class NoodlyStockTrackerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoodlyStockTrackerServiceApplication.class, args);
	}

}
