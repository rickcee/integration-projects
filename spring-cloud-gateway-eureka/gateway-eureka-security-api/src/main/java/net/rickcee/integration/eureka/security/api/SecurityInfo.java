package net.rickcee.integration.eureka.security.api;
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
public class SecurityInfo {

	private String id;
	private String type;
	private String isin;
	private String description;
	
}
