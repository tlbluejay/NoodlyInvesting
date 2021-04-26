package lewis.trenton.NoodlyStockTrackerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/track")
public class TrackerController {
	
	private final String alphaVantageKey = "CO6UUNDDBMR4PTI9";
	private final String polygonIOKey = "zQyBWDe2RCdduW8j0KpnNLzjilTPx_EK";

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
	
	@RequestMapping(path="/overview/{symbol}", method=RequestMethod.GET)
	public String searchOverview(@PathVariable String symbol) {
		ResponseEntity<String> response = this.restTemplate.getForEntity("https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + alphaVantageKey, String.class);
		System.out.println(response.getBody());
		return response.getBody();
	}
	
	@RequestMapping(path="/ticker/{symbol}", method=RequestMethod.GET)
	public String searchTicker(@PathVariable String symbol) {
		ResponseEntity<String> response = this.restTemplate.getForEntity("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + alphaVantageKey, String.class);
		System.out.println(response.getBody());
		return response.getBody();
	}
	
	@RequestMapping(path="/news/{symbol}", method=RequestMethod.GET)
	public String searchNews(@PathVariable String symbol) {
		ResponseEntity<String> response = this.restTemplate.getForEntity("https://api.polygon.io/v1/meta/symbols/" + symbol + "/news?perpage=5&page=1&apiKey=" + polygonIOKey, String.class);
		System.out.println(response.getBody());
		return response.getBody();
	}
}
