package net.rickcee.integration.eureka.customer.api;
/**
 * 
 */


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
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String phoneNumber;
	private String faxNumber;
	private Boolean eConfirmations;
	
}
