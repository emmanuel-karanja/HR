package com.atticus.hr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BootTestController {
 
	@GetMapping("/tests")
	public String getTests() {
		return "tests";
	}
}
