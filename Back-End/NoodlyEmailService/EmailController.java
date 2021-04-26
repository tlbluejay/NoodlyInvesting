package lewis.trenton.NoodlyEmailService;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/email")
public class EmailController {

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
	
	@RequestMapping(path="", method=RequestMethod.POST)
	public void ConstructAndSendHTTPEmail(@RequestBody String requestBody) {
		
		String[] requestBodyContent = requestBody.split(";");
		String from = "noodlynotification@gmail.com";
		String to = requestBodyContent[0];
		final String password = "NoodlyCap2020";
		
		try {
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.starttls.enable", "true");
			
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			};

			Session session = Session.getInstance(prop, auth);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject("Notification of Noodly Investing Activity");

			message.setContent(requestBodyContent[1], "text/plain");

			Transport.send(message);
			
			System.out.println("Message Sent to " + to);
			
		} catch (Exception ex) {
			System.out.println("Message Failed to be Sent to " + to);
		}
	}
}
