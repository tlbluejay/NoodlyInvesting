package lewis.trenton.NoodlyZuulGateway;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class MyAuthenticationFilter extends ZuulFilter {
	
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();
		String headerValue = req.getHeader("Authorization");
		String username = "", password = "";
		System.out.println("Zuul received a request from " + req.getRemoteAddr());
		System.out.println("Hostname: " + req.getRemoteHost() + "; Path: " + req.getRequestURI());
		
//		if (headerValue != null) {
//			String[] parts = headerValue.split(" ");
//			if (parts.length == 2) {
//				String encodedAuth = parts[1].trim();
//				String decodedAuth = new String(Base64.getDecoder().decode(encodedAuth));
//				String[] pieces = decodedAuth.split(":");
//				if (pieces.length == 2) {
//					username = pieces[0].trim();
//					password = pieces[1];
//					if (!USERNAME.equalsIgnoreCase(username) || !PASSWORD.equalsIgnoreCase(password)) {
//						throw new RuntimeException("Bad Credentials.");
//					}
//				}
//			}
//		}
		System.out.println("reached");
		return null;
	}
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
