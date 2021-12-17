package net.rickcee.integration.eureka.security.api;
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
@FeignClient("security-data-client")
public interface ISecurityController {

	@GetMapping("/security")
	Collection<SecurityInfo>  getSecurities();
	
	@GetMapping("/security/{id}")
	SecurityInfo getSecurity(@PathVariable("id") String id);

}
