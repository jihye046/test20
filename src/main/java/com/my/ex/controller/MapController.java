package com.my.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.ex.dto.map.KakaoMapRequestDto;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	private KakaoMapRequestDto dto;
	
	// 검색한 주소지를 지도로 보여줌
	@RequestMapping("/searchedAddMap")
	public String searchedAddMap(Model model) {
		model.addAttribute("jsKey", dto.getJsKey());
		return "/category/searchedAddMap";
	}
	
	// 특정 주소지를 지도로 보여줌
	@RequestMapping("/specificAddMap")
	public String showSpecificAddMap(Model model) {
		model.addAttribute("jsKey", dto.getJsKey());
		return "/category/specificAddMap";
	}
	
	// 현위치부터 특정 주소지까지 길찾기
	
	// 현위치부터 검색한 주소지까지 길찾기
}
