package com.my.ex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.ex.dao.UserDao;
import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserDao dao;
	
	// 회원가입
	@Override
	public boolean join(UserDto dto) {
		int result = dao.join(dto);
		return (result > 0) ? true : false;
	}

	// 로그인
	@Override
	public boolean login(String userId, String userPw) {
		UserDto dto = dao.login(userId, userPw);
		return (dto != null) ? true	: false;
	}

	@Override
	public String getUserNickname(String userId) {
		return dao.getUserNickname(userId);
	}

	@Override
	public List<BoardDto> getUserPosts(String userId) {
		return dao.getUserPosts(userId);
	}

	@Override
	public List<BoardDto> getUserComments(String userId) {
		return dao.getUserComments(userId);
	}

	@Override
	public List<BoardDto> getUserLikedPosts(String userId) {
		return dao.getUserLikedPosts(userId);
	}

	// 비밀번호 변경
	@Override
	public String checkCurrentPasswordAndChange(String userId, String oldPassword, String newPassword) {
		if (dao.isOldPasswordCorrect(userId, oldPassword) != 1) {
	        return "oldPasswordIncorrect"; // 현재 비밀번호가 틀린 경우
	    } else if (dao.getCurrentPassword(userId).equals(newPassword)) {
	    	return "isSameAsCurrentPassword"; // 현재 비밀번호와 새 비밀번호가 동일한 경우
	    }
	    return dao.updatePassword(userId, newPassword) ? "success" : "fail";
	}
	
	// 현재 프로필 정보 조회
	@Override
	public UserDto getCurrentProfile(String userId) {
		return dao.getCurrentProfile(userId);
	}
	
	// 닉네임만 변경하는 경우
	@Override
	public boolean updateNickname(Map<String, String> map) {
		return dao.updateNickname(map) > 0;
	}
	
	// 프로필 이미지만 변경하는 경우
	@Override
	public boolean updateProfileImage(Map<String, String> map) {
		return dao.updateProfileImage(map) > 0;
	}

	// 닉네임 + 프로필 이미지 변경하는 경우
	@Override
	public boolean updateNicknameAndProfileImage(Map<String, String> map) {
		return dao.updateNicknameAndProfileImage(map) > 0;
	}

	// 댓글 프로필 이미지 가져오기
	@Override
	public String getProfileFilename(String bName) {
		return dao.getProfileFilename(bName);
	}

	// 아이디 찾기 - 사용자 정보 확인
	@Override
	public boolean checkUserInfoMatch(String userName, String uemail) {
		HashMap<String, String> map = new HashMap<>();
		map.put("userName", userName);
		map.put("uemail", uemail);
		int selectCount = dao.checkUserInfoMatch(map);
		
		return selectCount > 0;
	}

	// 아이디 찾기 - 인증 후 사용자정보 가져오기
	@Override
	public List<UserDto> findUserIdByEmail(HashMap<String, String> hashMap) {
		return dao.findUserIdByEmail(hashMap);
	}
	
}
