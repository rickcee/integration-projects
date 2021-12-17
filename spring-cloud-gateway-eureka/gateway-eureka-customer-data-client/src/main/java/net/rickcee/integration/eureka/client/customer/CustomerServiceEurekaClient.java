package net.rickcee.integration.eureka.client.customer;

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

import net.rickcee.integration.eureka.customer.api.CustomerInfo;
import net.rickcee.integration.eureka.customer.api.ICustomerController;

@SpringBootApplication
public class CustomerServiceEurekaClient {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceEurekaClient.class, args);
	}
}

@RestController
class ServiceInstanceRestController implements ICustomerController {

	private static Map<String, CustomerInfo> custDb = new HashMap<>();

	static {
		custDb.put("1", new CustomerInfo("1", "Johnson & Johnson LLC", "130 Clearview Ave.","Fairfield","CT","06945","United States","+1 (203) 895-2377","+1 (203) 895-2388",true));
		custDb.put("2", new CustomerInfo("2", "Bayer & Associates", "Zeughofstrasse 22","Berlin","ZS","1121", "Germany","+49 301234567","+49 301233321",false));
		custDb.put("3", new CustomerInfo("3", "Mercado Libre", "Juan B. Justo 2376","Ciudad Autonoma de Buenos Aires","CF","2890", "Argentina","+54 11 6690-2491","+54 11 6690-3366",false));
		custDb.put("4", new CustomerInfo("4", "Apex Industries Inc.", "Seaview 1 Blvd.","Seaview","FL","33512", "United States","+1 (411) 502-2253","+1 (411) 502-2254",true));
	}

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@Override
	public CustomerInfo getCustomer(String id) {
		System.out.println("getCustomer(id) - " + id);
		return custDb.get(id);
	}

	@Override
	public Collection<CustomerInfo> getCustomers() {
		System.out.println("getCustomers()");
		return custDb.values();
	}

}