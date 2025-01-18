package com.my.ex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;
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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session, RedirectAttributes rttr) {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		boolean loginResult = service.login(userId, userPw);
		if(loginResult) {
			session.setAttribute("userId", userId);
			String userNickname = service.getUserNickname(userId);
			session.setAttribute("userNickname", userNickname);
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
	
	@RequestMapping("/myPage")
	public String myPage() {
		return "/user/myPage";
	}
	
	private String getUserIdFromSession(HttpSession session) {
	    return (String) session.getAttribute("userId");
	}
	
	// 내가 작성한 게시글
	@RequestMapping("/getUserPosts")
	public String getUserPosts(HttpSession session, Model model) {
		String userId = getUserIdFromSession(session);
		List<BoardDto> list = service.getUserPosts(userId);
		model.addAttribute("getUserPosts", list);
		return "/user/getUserPosts";
	}
	
	// 내가 작성한 댓글
	@RequestMapping("/getUserComments")
	public String getUserComments(HttpSession session, Model model) {
		String userId = getUserIdFromSession(session);
		List<BoardDto> list = service.getUserComments(userId);
		model.addAttribute("getUserComments", list);
		return "/user/getUserComments";
	}
	
	// 좋아요한 게시글
	@RequestMapping("/getUserLikedPosts")
	public String getUserLikedPosts(HttpSession session, Model model) {
		String userId = getUserIdFromSession(session);
		List<BoardDto> list = service.getUserLikedPosts(userId);
		model.addAttribute("getUserLikedPosts", list);
		return "/user/getUserLikedPosts";
	}
	
	// 비밀번호 변경 페이지
	@RequestMapping("/changePasswordForm")
	public String changePasswordForm(HttpSession session, Model model) {
		return "/user/changePassword"; 
	}
	
	// 비밀번호 변경
	/*
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public void changePassword(HttpServletRequest request) {
		String oldPassword = request.getParameter("oldPw");
	    String newPassword = request.getParameter("newPw");
	    
		System.out.println("oldPassword: " + oldPassword);
		System.out.println("newPassword: " + newPassword);
	}
	*/
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> changePassword(@RequestBody Map<String, String> requestBody) {
//		String oldPassword = request.getParameter("oldPw");
//	    String newPassword = request.getParameter("newPw");
		String oldPassword = requestBody.get("oldPw");
	    String newPassword = requestBody.get("newPw");
		
		System.out.println("oldPassword: " + oldPassword);
		System.out.println("newPassword: " + newPassword);
		
		Map<String, String> response = new HashMap<>();
	    response.put("status", "success");
	    return response;
	}
}
