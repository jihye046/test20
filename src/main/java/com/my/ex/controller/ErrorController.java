package com.my.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/error404")
	public String error404() {
		return "/error/error404";
	}
	
	@RequestMapping("/error500")
	public String error500() {
		return "/error/error500";
	}
}
