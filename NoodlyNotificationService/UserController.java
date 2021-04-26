package lewis.trenton.NoodlyNotificationService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserJpaRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@RequestMapping(path = "/showSubscribers", method = RequestMethod.GET)
	public List<User> allSubscribers() {
		return userRepository.findAll();
	}
	
	@RequestMapping(path = "/unsubscribeAll", method = RequestMethod.GET)
	public String unsubscribeAll() {
		userRepository.deleteAll();
		return "Subscribers list has been emptied. New Count: " + userRepository.count();
	}
	
	@RequestMapping(path = "/notifyAll", method = RequestMethod.GET)
	public void emailAllUsers() {
		System.out.println(userRepository.count());
		
		for (User user : userRepository.findAll()) {
			if (user.email != null) {
				List<ServiceInstance> instances = discoveryClient.getInstances("email-service");
				List<String> contactInfos = instances.stream()
						.map(si -> si.getInstanceId() + ";" + si.getHost() + ":" + si.getPort() + "/" + si.getUri().toString())
						.collect(Collectors.toList());
				System.out.println(contactInfos);
				
				ServiceInstance serviceInstance = loadBalancerClient.choose("email-service");
				System.out.println("email service found at:" + serviceInstance.getUri());
				this.restTemplate.postForEntity(serviceInstance.getUri() + "emailsvc/email", user.email, String.class);
			}
		}
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void newSubscriber(@RequestBody String email) {
		User u = new User();
		u.setEmail(email);
		u.setId(userRepository.findAll().size() + 1);
		userRepository.save(u);
		List<ServiceInstance> instances = discoveryClient.getInstances("email-service");
		List<String> contactInfos = instances.stream()
				.map(si -> si.getInstanceId() + ";" + si.getHost() + ":" + si.getPort() + "/" + si.getUri().toString())
				.collect(Collectors.toList());
		System.out.println(contactInfos);

		ServiceInstance serviceInstance = loadBalancerClient.choose("email-service");
		System.out.println("email service found at:" + serviceInstance.getUri());
		this.restTemplate.postForEntity(serviceInstance.getUri() + "emailsvc/email", email, String.class);
	}
	
	
	
	
}
