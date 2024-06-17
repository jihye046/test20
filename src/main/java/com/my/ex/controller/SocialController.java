package com.my.ex.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ex.dto.SocialDto;
import com.my.ex.dto.google.GoogleCallbackDto;
import com.my.ex.dto.google.GoogleProfileApi;
import com.my.ex.dto.google.GoogleToken;
import com.my.ex.dto.naver.NaverCallbackDto;
import com.my.ex.dto.naver.NaverProfileApi;
import com.my.ex.dto.naver.NaverToken;
import com.my.ex.service.SocialService;

@Controller
@RequestMapping("/social")
public class SocialController {
	
	@Autowired
	private NaverCallbackDto naverCallbackDto;
	
	@Autowired
	private GoogleCallbackDto googleCallbackDto;
	
	@Autowired
	private SocialService service;
	
	GoogleToken token;
	
	// 네이버 로그인 연동 URL 생성
	@RequestMapping("/naverLogin")
	public String naverLogin(HttpServletRequest request) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
		return "redirect:" + service.getNaverAuthorizeUrl("authorize");
	}
	
	// 네이버 로그인 연동 결과 callback
	@RequestMapping("/naverCallback")
	public String naverCallback(HttpServletRequest request) {
		naverCallbackDto.setCallbackCode(request.getParameter("code"));
		naverCallbackDto.setCallbackState(request.getParameter("state"));
		naverCallbackDto.setCallbackError(request.getParameter("error"));
		naverCallbackDto.setCallbackError_Description(request.getParameter("error_description"));
		return "redirect:naverGetUserInfo";
	}
	
	// callback 성공 시 받은 code를 이용하여 accessToken을 발급 받고 이를 이용하여 사용자 정보 얻기 
	@RequestMapping("naverGetUserInfo")
	public String naverGetUserInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws URISyntaxException, Exception {
		if(naverCallbackDto.getCallbackError() == null || naverCallbackDto.getCallbackError() == "") {
			ObjectMapper mapper = new ObjectMapper();
			// 응답받은 json 데이터를 해당 클래스 객체로 변환, JSON 데이터의 '키'와 클래스의 멤버 변수 이름이 일치하는 경우 자동으로 매핑
			// code 주고 Accesstoken 받기
			String responseToken = service.getNaverTokenUrl("token", "authorization_code", naverCallbackDto);
			NaverToken token = mapper.readValue(responseToken, NaverToken.class); // Accesstoken 매핑
			
			// Accesstoken 주고 userInfo 받기
			String responseUser = service.getNaverUserByToken(token);
			NaverProfileApi naverUser = mapper.readValue(responseUser, NaverProfileApi.class); // userInfo 매핑

			SocialDto dto = new SocialDto();
			dto.setSns_id(naverUser.getResponse().getId());
			dto.setSns_nickName(naverUser.getResponse().getNickname());
			dto.setSns_email(naverUser.getResponse().getEmail());;
			dto.setSns_name(naverUser.getResponse().getName());
			dto.setSns_mobile(naverUser.getResponse().getMobile());
			dto.setSns_type("naver");
			dto.setSns_profile(naverUser.getResponse().getProfile_image());
			
			boolean result = service.checkSocialIdExist(dto.getSns_id());
			if(!result) service.socialJoin(dto); 
			return targetLocation(session, dto);
		} else {
			System.out.println(naverCallbackDto.getCallbackError_Description());
			return "/user/loginPage";
		}
	}
	
	// 구글 로그인 연동 URL 생성
	@RequestMapping("/googleLogin")
	public String googleLogin(HttpServletRequest request) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
		return "redirect:" + service.getGoogleAuthorizeUrl();
	}
	
	// 구글 로그인 연동 결과 callback
	@RequestMapping("/googleCallback")
	public String googleCallback(HttpServletRequest request) {
		googleCallbackDto.setCallbackCode(request.getParameter("code"));
		googleCallbackDto.setCallbackState(request.getParameter("state"));
		googleCallbackDto.setCallbackError(request.getParameter("error"));
		googleCallbackDto.setCallbackError_Description(request.getParameter("error_description"));
		return "redirect:getGoogleToken";
	}
	
	// callback 성공 시 받은 code를 이용하여 accessToken을 발급 받고 이를 이용하여 사용자 정보 얻기 
	@RequestMapping("/getGoogleToken")
	public String getGoogleToken(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws URISyntaxException, Exception {
		if(googleCallbackDto.getCallbackError() == null || googleCallbackDto.getCallbackError() == "") {
			ObjectMapper mapper = new ObjectMapper();
			// code 주고 Accesstoken 받기
			String responseToken = service.getGoogleTokenUrl("token", "authorization_code", googleCallbackDto);
			GoogleToken token = mapper.readValue(responseToken, GoogleToken.class);
			
			// Accesstoken 주고 userInfo 받기
			String responseUser = service.getGoogleUserByToken("tokeninfo", token);
			GoogleProfileApi googleUser = mapper.readValue(responseUser, GoogleProfileApi.class);
			
			SocialDto dto = new SocialDto();
			dto.setSns_id(googleUser.getSub());
			dto.setSns_email(googleUser.getEmail());
			dto.setSns_profile(googleUser.getPicture());
			dto.setSns_type("google");
			String name = googleUser.getName();
			String family_name = googleUser.getFamily_name();
			dto.setSns_name((name == null ? "" : name) + (family_name == null ? "" : family_name));
			
			boolean result = service.checkSocialIdExist(dto.getSns_id());
			if(result) {
				return targetLocation(session, dto);
			} else {
				model.addAttribute("socialDto", dto);
				return "/user/socialJoinPage";
			}
		} else {
			System.out.println(googleCallbackDto.getCallbackError_Description());
			return "/user/loginPage";
		}
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String checkSocialIdExist(HttpSession session, SocialDto dto) {
		service.socialJoin(dto); 
		return targetLocation(session, dto);
	}
	
	public String targetLocation(HttpSession session, SocialDto dto) {
		session.setAttribute("userId", dto.getSns_id());
		session.setAttribute("userNickname", dto.getSns_nickName());
		String targetLocation = (String)session.getAttribute("targetLocation");
		String redirectLocation = (targetLocation != null) ? "redirect:" + targetLocation : "redirect:/board/paging";
		return redirectLocation;
	}
	
}
