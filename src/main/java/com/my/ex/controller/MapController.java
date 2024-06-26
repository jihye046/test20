package com.my.ex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ex.dto.map.KakaoMapRequestDto;
import com.my.ex.dto.map.NaverMapRequestDto;
import com.my.ex.service.MapService;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	private MapService service;
	
	@Autowired
	private KakaoMapRequestDto kakaoMapDto;
	
	@Autowired
	private NaverMapRequestDto naverMapDto;
	
	// 검색한 주소지를 지도로 보여줌
	@RequestMapping("/searchedAddMap")
	public String searchedAddMap(Model model) {
		model.addAttribute("jsKey", kakaoMapDto.getJsKey());
		return "/category/searchedAddMap";
	}
	
	// 특정 주소지를 지도로 보여줌
	@RequestMapping("/specificAddMap")
	public String showSpecificAddMap(Model model) {
		model.addAttribute("jsKey", kakaoMapDto.getJsKey());
		return "/category/specificAddMap";
	}
	
	// 현위치부터 검색한 주소지까지 길찾기
	@RequestMapping("/findRoutePage")
	public String findRoutePage(Model model) {
		model.addAttribute("jsKey", kakaoMapDto.getJsKey());
		model.addAttribute("naverMap_ClientId", naverMapDto.getClient_id());
		model.addAttribute("naverMap_ClientSecret", naverMapDto.getClient_secret());
		return "/category/findRoute";
	}
	
	@RequestMapping("/findRoute")
	@ResponseBody
	public void findRoute(@RequestBody Map<String, String> coordinates) {
		String response = service.findRoute("driving", coordinates);
		System.out.println("response: " + response); // json 매핑은 안하고 응답값 확인까지만 ok
//		ObjectMapper mapper = new ObjectMapper();
//		WeeklyWeatherDto weeklyWeatherDto = mapper.readValue(response, WeeklyWeatherDto.class);
	}

}
