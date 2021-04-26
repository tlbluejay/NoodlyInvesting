package lewis.trenton.NoodlyUserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserJpaRepository userRepository;
	
	@RequestMapping(path="/subscribe", method=RequestMethod.POST)
	public String SubscribeUser(@RequestBody String email) {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("notification-service");
		List<String> contactInfos = serviceInstances.stream()
				.map(si -> si.getInstanceId() + ";" + si.getHost() + ":" + si.getPort() + "/" + si.getUri().toString())
				.collect(Collectors.toList());
		System.out.println(contactInfos);
		
		ServiceInstance serviceInstance = loadBalancerClient.choose("NoodlyNotificationService");
		System.out.println("Notification Service found at: " + serviceInstance.getUri());
		this.restTemplate.postForEntity(serviceInstance.getUri() + "notification-service/users", email, String.class);
		return email + " has been added to notification list.";
	}
	
	@RequestMapping(path="/retrieveUser", method=RequestMethod.GET)
	public User retrieveUser(@RequestBody String email ) {
		return userRepository.findByEmail(email);
	}
	
	@RequestMapping(path="/retrieve/{email}", method=RequestMethod.GET)
	public long retrieveID(@PathVariable String email) {
		User u = userRepository.findByEmail(email);
		if (u == null) {
			return -1;
		} else {
			return u.getId(); 			
		}
	}
	
	@RequestMapping(path="/clear", method=RequestMethod.GET)
	public void clear() {
		userRepository.deleteAll();
	}
	
	@RequestMapping(path="/count", method=RequestMethod.GET)
	public long countIndices() {
		return userRepository.count();
	}
	
	@RequestMapping(path="/logIn/{email}", method=RequestMethod.GET)
	public String logIn(@PathVariable String email) {
		User checkAgainst = userRepository.findByEmail(email);
		if (checkAgainst == null) {
			return "User not found at that email";
		} else {
			return checkAgainst.getPassword();
		}
	}
}
