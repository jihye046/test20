package com.my.ex.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.ex.dto.NaverCallbackDto;
import com.my.ex.dto.NaverProfileApi;
import com.my.ex.dto.NaverToken;
import com.my.ex.dto.UserDto;
import com.my.ex.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private NaverCallbackDto naverCallbackDto;
	
	@RequestMapping("/loginPage")
	public String loginPage() {
		return "/user/loginPage";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session, RedirectAttributes rttr) {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		boolean loginResult = service.login(userId, userPw);
		if(loginResult) {
			session.setAttribute("userId", userId);
			String targetLocation = (String)session.getAttribute("targetLocation");
			return (targetLocation != null) ? "redirect:" + targetLocation : "redirect:/board/paging";
		} else {
			return "redirect:loginPage";
		}
	}
	
	@RequestMapping("/joinPage")
	public String joinPage() {
		return "/user/joinPage";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserDto dto, RedirectAttributes rttr, HttpSession session) {
		boolean joinResult = service.join(dto);
		String result = "false";
		if(joinResult) {
			result = "true";
			session.setAttribute("userId", dto.getUserId());
		}
		rttr.addFlashAttribute("joinResult", result);
		return "redirect:loginPage";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 자체를 삭제
		return "redirect:loginPage";
	}
	
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
	public void naverGetUserInfo(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, Exception {
		if(naverCallbackDto.getCallbackError() == null || naverCallbackDto.getCallbackError() == "") {
			String responseToken = service.getNaverTokenUrl("token", "authorization_code", naverCallbackDto);
			ObjectMapper mapper = new ObjectMapper();
			// 응답받은 json 데이터를 해당 클래스 객체로 변환, JSON 데이터의 '키'와 클래스의 멤버 변수 이름이 일치하는 경우 자동으로 매핑
			NaverToken token = mapper.readValue(responseToken, NaverToken.class); 
			String responseUser = service.getNaverUserByToken(token);
			NaverProfileApi naverUser = mapper.readValue(responseUser, NaverProfileApi.class);
			System.out.println(naverUser.toString());
		} else {
			System.out.println(naverCallbackDto.getCallbackError_Description());
		}
	}
	
}
