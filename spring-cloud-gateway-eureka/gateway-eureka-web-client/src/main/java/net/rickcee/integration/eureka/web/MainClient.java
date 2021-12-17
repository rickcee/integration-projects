package net.rickcee.integration.eureka.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-eureka-web-client")
public interface MainClient {
    @RequestMapping("/main")
    String main();
}