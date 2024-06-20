package com.my.ex.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

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
import com.my.ex.dto.weather.WeeklyWeatherDto;
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
								  @RequestParam("longitude") double longitude, HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		String response = service.getWeather("weather", latitude, longitude);
		ObjectMapper mapper = new ObjectMapper();
		WeatherDto currentWeatherDto = mapper.readValue(response, WeatherDto.class);
		session.setAttribute("latitude", latitude);
		session.setAttribute("longitude", longitude);
		return new ResponseEntity<>(currentWeatherDto, HttpStatus.OK);
	}
	
	@RequestMapping("/getWeeklyWeather")
	@ResponseBody
	public ResponseEntity<WeeklyWeatherDto> getWeeklyWeather(@RequestParam("latitude") double latitude,
			@RequestParam("longitude") double longitude, HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		String response = service.getWeather("forecast", latitude, longitude);
		ObjectMapper mapper = new ObjectMapper();
		WeeklyWeatherDto weeklyWeatherDto = mapper.readValue(response, WeeklyWeatherDto.class);
		return new ResponseEntity<>(weeklyWeatherDto, HttpStatus.OK);
	}
	
	@RequestMapping("/info")
	public String info() {
		return "/category/info";
	}
	
}
