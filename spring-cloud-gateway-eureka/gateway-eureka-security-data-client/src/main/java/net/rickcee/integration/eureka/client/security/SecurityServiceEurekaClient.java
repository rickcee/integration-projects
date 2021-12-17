package net.rickcee.integration.eureka.client.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.rickcee.integration.eureka.security.api.ISecurityController;
import net.rickcee.integration.eureka.security.api.SecurityInfo;

@SpringBootApplication
public class SecurityServiceEurekaClient {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceEurekaClient.class, args);
	}
}

@RestController
class ServiceInstanceRestController implements ISecurityController {

	private static Map<String, SecurityInfo> custDb = new HashMap<>();

	static {
		custDb.put("1", new SecurityInfo("1", "Bond", "USXXXXXXXXX8", "Johnson & Johnson Bond 2045"));
		custDb.put("2", new SecurityInfo("2", "Bond", "DEXXXXXXXXX5", "Bayer & Associates Bond 2040"));
		custDb.put("3", new SecurityInfo("3", "Bond", "ARXXXXXXXXX3", "Mercado Libre Bond 2048"));
	}

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@Override
	public SecurityInfo getSecurity(String id) {
		System.out.println("getSecurity(id) - " + id);
		return custDb.get(id);
	}

	@Override
	public Collection<SecurityInfo> getSecurities() {
		System.out.println("getSecurities()");
		return custDb.values();
	}
}