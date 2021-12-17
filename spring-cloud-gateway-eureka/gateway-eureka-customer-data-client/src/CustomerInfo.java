/**
 * 
 */
package net.rickcee.integration.eureka.client.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author rickcee
 *
 */
@Data
@AllArgsConstructor
public class CustomerInfo {

	private String id;
	private String name;
	private String country;
}
