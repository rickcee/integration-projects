package net.rickcee.integration.angular.login.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Slf4j
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure()");
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> ima = auth.inMemoryAuthentication();
		ima.withUser("admin").password("{noop}admin").roles("ADMIN");
		ima.withUser("operator1").password("{noop}operator1").roles("OPERATOR");
		ima.withUser("user1").password("{noop}password1").roles("USER");
		ima.withUser("user2").password("{noop}password2").roles("USER");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				log.info("Adding CORS Mapping");
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "GET", "OPTIONS", "PUT", "DELETE")
						.allowedHeaders("x-requested-with", "Content-Type", "Origin", "Authorization", "Accept", "Referer", "User-Agent",
								"client-security-token", "X-Auth-Token").allowCredentials(true).maxAge(4800);
			}
		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated()
		.and().httpBasic().and().cors();
	}
}