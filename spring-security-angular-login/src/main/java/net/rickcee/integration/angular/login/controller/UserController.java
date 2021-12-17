package net.rickcee.integration.angular.login.controller;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.rickcee.integration.angular.login.dto.LoginRequest;
import net.rickcee.integration.angular.login.dto.LoginResponse;

@RestController
@CrossOrigin
@Slf4j
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	public UserController() {
		log.info("UserController()");
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object login(@RequestBody LoginRequest loginRequest) {
		System.out.println("a");
		log.info("login(): {}", loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		LoginResponse resp = new LoginResponse();
		//String jwt = jwtUtils.generateJwtToken(authentication);
		resp.setToken(UUID.randomUUID().toString());
		resp.setUsername(loginRequest.getUsername());
		authentication.getAuthorities().forEach(a -> {
			resp.getRoles().add(a.getAuthority());
		});
		
		return ResponseEntity.ok(resp);
    }
    
    @RequestMapping(path = "/secure/service1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Object adminSvc() {
    	Map<String, String> map = Stream.of(new String[][] {
    		  { "service", "ADMIN Service 1" }, 
    		  { "description", "Only a person with ROLE_ADMIN can invoke this." }, 
    		}).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    	return map;
    }
    
    @RequestMapping(path = "/secure/service2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_OPERATOR")
    public Object operatorSvc() {
    	Map<String, String> map = Stream.of(new String[][] {
    		  { "service", "OPERATOR Service 1" }, 
    		  { "description", "Only a person with ROLE_OPERATOR can invoke this." }, 
    		}).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    	return map;
    }
    
    @RequestMapping(path = "/secure/service3", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public Object userSvc1() {
    	Map<String, String> map = Stream.of(new String[][] {
    		  { "service", "USER Service 1" }, 
    		  { "description", "Only a person with ROLE_USER can invoke this." }, 
    		}).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    	return map;
    }
    
    @RequestMapping(path = "/secure/service4", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public Object userSvc2() {
    	Map<String, String> map = Stream.of(new String[][] {
    		  { "service", "USER Service 2" }, 
    		  { "description", "Only a person with ROLE_USER can invoke this." }, 
    		}).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    	return map;
    }

}
