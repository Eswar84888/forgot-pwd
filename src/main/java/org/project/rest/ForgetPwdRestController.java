package org.project.rest;

import org.project.service.ForgotPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgetPwdRestController {

	@Autowired
	private ForgotPwdService service;
	
	@PostMapping("/forgotpwd/{emaail}")
	public String forgotpwd(@PathVariable String email ) {
		return service.forgotpwd(email);
		
	}
}
