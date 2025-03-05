package com.my.ex.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;
import com.my.ex.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	// 세션에 저장된 userId 가져오기
	public static String getUserIdFromSession(HttpSession session) {
	    return (String) session.getAttribute("userId");
	}
	
	// 로그인 페이지
	@RequestMapping("/loginPage")
	public String loginPage() {
		return "/user/loginPage";
	}
	
	// 로그인
	// 로그인 전 위치로 이동
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
			rttr.addFlashAttribute("loginFail", true);
			return "redirect:loginPage";
		}
	}
	
	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 자체를 삭제
		return "redirect:loginPage";
	}
	
	// 회원가입 페이지
	@RequestMapping("/joinPage")
	public String joinPage() {
		return "/user/joinPage";
	}
	
	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserDto dto, RedirectAttributes rttr, HttpSession session) {
		boolean joinResult = service.join(dto);
		if(joinResult) {
			session.setAttribute("userId", dto.getUserId());
		}
		rttr.addFlashAttribute("joinResult", true);
		return "redirect:loginPage";
	}

	// 마이페이지
	@RequestMapping("/myPage")
	public String myPage() {
		return "/user/myPage";
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
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> changePassword(HttpSession session, @RequestBody Map<String, String> requestBody) {
		String userId = getUserIdFromSession(session);
		String oldPassword = requestBody.get("oldPw");
	    String newPassword = requestBody.get("newPw");
	    
	    String updateResult = service.checkCurrentPasswordAndChange(userId, oldPassword, newPassword);
	    Map<String, String> response = new HashMap<>();
	    if(updateResult.equals("success")) {
	    	session.invalidate(); // 비밀번호 변경 성공 시 로그아웃 처리
	    	response.put("status", "success");
	    	response.put("msg", "비밀번호가 변경되었습니다. 다시 로그인해 주세요.");
	    } else if(updateResult.equals("oldPasswordIncorrect")) {
	    	response.put("status", "oldPasswordIncorrect");
	    	response.put("msg", "현재 비밀번호가 일치하지 않습니다.");
	    } else if(updateResult.equals("isSameAsCurrentPassword")) {
	    	response.put("status", "isSameAsCurrentPassword");
	    	response.put("msg", "현재 비밀번호와 새 비밀번호가 동일합니다. 다른 비밀번호로 변경해주세요.");
	    } else {
	    	response.put("status", "error");
	    	response.put("msg", "알 수 없는 오류가 발생했습니다.");
	    }
	    return response;
	}
	
	// 프로필 변경 페이지
	@RequestMapping("/updateProfileForm")
	public String updateProfileForm(HttpSession session, Model model) {
		UserDto currentProfile = service.getCurrentProfile(getUserIdFromSession(session));
		System.out.println("db_unickname: " + currentProfile.getUnickName());
		System.out.println("db_profileImage: " + currentProfile.getUprofile_image());
//		model.addAttribute("currentNickname", currentProfile.getUnickName());
		model.addAttribute("uprofile_image", currentProfile.getUprofile_image());
		return "/user/updateProfile";
	}
	
	// 프로필 변경
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateProfile (
		@RequestParam(value = "nickname", required = false) String nickname,
		@RequestParam(value = "defaultImage", required = false) String defaultImage,
		@RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
		@RequestParam(value = "isCustomImageSelected", defaultValue = "false") boolean isCustomImageSelected,
		HttpSession session) throws IOException {
		
		Map<String, String> response = new HashMap<>();
		
		Map<String, String> map = new HashMap<>();
		String userId = getUserIdFromSession(session);
//		String defaultImg = "profile_default.png";
		map.put("userId", userId);
		
		boolean updateResult = false;
		// 업로드된 이미지가 있는 상태에서 닉네임만 수정하는 경우 기본 이미지로 바뀌는 문제 해결하기
		
		// 닉네임만 변경하는 경우
		if((defaultImage == null && profileImage == null) && nickname != null) {
			map.put("nickname", nickname);
			updateResult = service.updateNickname(map);
		}
		// 프로필 이미지만 기본 이미지로 변경하는 경우
		else if(defaultImage != null && !isCustomImageSelected && nickname == null) {
			map.put("filename", defaultImage);
			updateResult = service.updateProfileImage(map);
		} // 기본이미지가 db에 저장이 안됨
		// 프로필 이미지만 업로드한 이미지로 변경하는 경우
		else if(profileImage != null && isCustomImageSelected && nickname == null) {
			String filename = saveProfileImage(profileImage, userId);
			map.put("filename", filename);
			updateResult = service.updateProfileImage(map);
		}
		// 닉네임 변경 + 기본 이미지로 변경하는 경우
		else if(defaultImage != null && !isCustomImageSelected && nickname != null) {
			map.put("nickname", nickname);
			map.put("filename", defaultImage);
			updateResult = service.updateNicknameAndProfileImage(map);
		}
		// 닉네임 변경 + 업로드한 이미지로 변경하는 경우
		else if(profileImage != null && isCustomImageSelected && nickname != null) {
			String filename = saveProfileImage(profileImage, userId);
			map.put("nickname", nickname);
			map.put("filename", filename);
			updateResult = service.updateNicknameAndProfileImage(map);
		} 
		
		// 응답 처리
		if(updateResult) {
			response.put("status", "success");
			response.put("msg", "프로필이 수정되었습니다.");
			if(nickname != null) { // 닉네임 변경을 한 경우
				session.setAttribute("userNickname", nickname);
			}
		} else {
			response.put("status", "fail");
			response.put("msg", "알 수 없는 오류가 발생했습니다.");
		}
		System.out.println("session에 저장된 nickname: " + session.getAttribute("userNickname"));
		return response;
	}
	
	// 프로필 이미지 파일 서빙
	@RequestMapping("/getProfileImage/{filename:.+}")
	@ResponseBody
	public Resource getProfileImage(@PathVariable String filename) {
		String uploadDir = "C:\\server_program\\profileImgTest\\";
		File file = new File(uploadDir, filename);
		
		// 로그
	    System.out.println("파일 요청: " + filename);
	    System.out.println("파일 실제 경로: " + file.getAbsolutePath());
	    System.out.println("파일 존재 여부: " + file.exists());

		
		if (file.exists()) {
			return new FileSystemResource(file); // file자체를 보내는 것은 X, HTTP 본문 응답으로 자동 변환해주는 API(FileSystemResource())를 이용해서 보내야 함
		} else {
			throw new RuntimeException("File not found");
		}
	}
	
	// 파일 저장 로직
	private String saveProfileImage(MultipartFile profileImage, String userId) throws IOException {
		// 파일 저장 경로 설정
		String uploadDir = "C:\\server_program\\profileImgTest\\";
		String filename = userId + "_" + profileImage.getOriginalFilename();
		File fileToSave = new File(uploadDir, filename);
		
		// 파일 저장
		profileImage.transferTo(fileToSave);
		return filename;
	}
	
	// 1:1 채팅
	@RequestMapping("/chat")
	public String chatForm() {
		return "/user/chatPage";
	}
	
}
