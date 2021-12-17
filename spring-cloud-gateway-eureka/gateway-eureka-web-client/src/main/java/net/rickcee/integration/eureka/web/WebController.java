package net.rickcee.integration.eureka.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.rickcee.integration.eureka.customer.api.ICustomerController;
import net.rickcee.integration.eureka.security.api.ISecurityController;

@Controller
public class WebController {
	
    @Autowired
    private ICustomerController customerClient;
	
    @Autowired
    private ISecurityController securityClient;

    @RequestMapping("/main-page")
	public String greeting(@RequestParam("id") String id, Model model) {
		model.addAttribute("mainMessage", "Hello from Eureka Web Client");
		model.addAttribute("customers", customerClient.getCustomers());
		model.addAttribute("securities", securityClient.getSecurities());
		model.addAttribute("selectedCustomer", customerClient.getCustomer(id));
		return "main-view";
    }
}