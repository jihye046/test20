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

import com.my.ex.dto.SocialDto;
import com.my.ex.dto.UserDto;
import com.my.ex.service.SocialLogin;
import com.my.ex.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/loginPage")
	public String loginPage() {
		return "/user/loginPage";
	}
	
	@Autowired
	private SocialLogin social;
	
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
	
	@RequestMapping("/naverLogin")
	public void naverLogin(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
//		SocialDto dto = new SocialDto();
		social.getNaverAuthorizeUrl("authorize");
//		String url = service.getNaverAuthorizeUrl("authorize");
		/*
		try {
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
//		dto.abc();
	}
	
	@RequestMapping("/naverCallback")
	public void naverCallback() {
		System.out.println("naverCallback Controller 들어옴");
	}
	
}
