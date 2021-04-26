package lewis.trenton.NoodlyZuulGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class NoodlyZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoodlyZuulGatewayApplication.class, args);
	}
	
//	@Bean
//	public MyAuthenticationFilter myAuthenticationFilter() {
//		return new MyAuthenticationFilter();
//	}

}
