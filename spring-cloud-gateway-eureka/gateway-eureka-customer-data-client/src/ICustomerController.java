/**
 * 
 */
package net.rickcee.integration.eureka.client.customer;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author rickcee
 *
 */
public interface ICustomerController {

	@GetMapping("/customer")
	Collection<CustomerInfo>  getCustomers();
	
	@GetMapping("/customer/{id}")
	CustomerInfo getCustomer(@PathVariable("id") String id);

}
