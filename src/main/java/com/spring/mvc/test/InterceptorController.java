package com.spring.mvc.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterceptorController {

	@GetMapping("/test1")
	public String test1() {
		return "test/inter-test";
	}
	
	@GetMapping("/test2")
	public String test2(Model model) {
		
		model.addAttribute("data", "Hello World!");
		return "test/inter-test";
	}
}









