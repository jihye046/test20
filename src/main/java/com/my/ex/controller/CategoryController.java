package com.my.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@RequestMapping("/weather")
	public String weather() {
		return "/category/weather";
	}
	
	@RequestMapping("/info")
	public String info() {
		return "/category/info";
	}	
	
}
