package net.rickcee.integration.eureka.customer.api;
/**
 * 
 */


import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author rickcee
 *
 */
@FeignClient("customer-data-client")
public interface ICustomerController {

	@GetMapping("/customer")
	Collection<CustomerInfo>  getCustomers();
	
	@GetMapping("/customer/{id}")
	CustomerInfo getCustomer(@PathVariable("id") String id);

}
