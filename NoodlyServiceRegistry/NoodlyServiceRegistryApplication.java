package lewis.trenton.NoodlyServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NoodlyServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoodlyServiceRegistryApplication.class, args);
	}
	
}
