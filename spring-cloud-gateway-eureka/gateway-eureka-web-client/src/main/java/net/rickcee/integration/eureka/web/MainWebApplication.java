/**
 * 
 */
package net.rickcee.integration.eureka.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author rickcee
 *
 */
@SpringBootApplication
@Configuration
@EnableWebMvc
@EnableFeignClients(basePackages = { "net.rickcee.integration.eureka.customer.api", "net.rickcee.integration.eureka.security.api" })
public class MainWebApplication extends SpringBootServletInitializer {

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	public static void main(String[] args) {
		SpringApplication.run(MainWebApplication.class, args);
	}

}
