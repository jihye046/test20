package com.my.ex.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ex.dto.weather.WeatherDto;
import com.my.ex.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;

	@RequestMapping("/weather")
	public String weather() {
		return "/category/weather";
	}
	
	@RequestMapping("/getCurrentWeather")
	@ResponseBody
	public ResponseEntity<WeatherDto> getCurrentWeather(@RequestParam("latitude") double latitude,
								  @RequestParam("longitude") double longitude) throws JsonParseException, JsonMappingException, IOException {
		String response = service.getCourrentWeather("weather", latitude, longitude);
		ObjectMapper mapper = new ObjectMapper();
		WeatherDto weatherDto = mapper.readValue(response, WeatherDto.class);
		return new ResponseEntity<>(weatherDto, HttpStatus.OK);
	}
	
	@RequestMapping("/info")
	public String info() {
		return "/category/info";
	}	
	
}