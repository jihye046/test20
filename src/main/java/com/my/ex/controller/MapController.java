package com.my.ex.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ex.dto.map.KakaoMapRequestDto;
import com.my.ex.dto.map.NaverMapRequestDto;
import com.my.ex.dto.weather.WeeklyWeatherDto;
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
	
	// 현위치부터 특정 주소지까지 길찾기
	@RequestMapping("/findRoutePage")
	public String findRoutePage(Model model) {
		model.addAttribute("jsKey", kakaoMapDto.getJsKey());
		model.addAttribute("naverMap_ClientId", naverMapDto.getClient_id());
		model.addAttribute("naverMap_ClientSecret", naverMapDto.getClient_secret());
		return "/category/findRoute";
	}
	
	@RequestMapping("/findRoute")
	@ResponseBody
	public void findRoute(Model model, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		String startAddress = request.getParameter("startAddress");
		String goalAddress = request.getParameter("goalAddress");
		System.out.println("startAddr: " + startAddress);
		System.out.println("goalAddr: " + goalAddress);
		//String result = service.getAddress("https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving");
//		System.out.println(result);
	}
	
	// 현위치부터 검색한 주소지까지 길찾기
}
