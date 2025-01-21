package com.my.ex.service;

import java.util.List;

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
	
	// 현재 닉네임 조회
	@Override
	public String getCurrentNickname(String userId) {
		return dao.getCurrentNickname(userId);
	}
	
	// 닉네임 변경
	@Override
	public String changeNickname(String userId, String unickname) {
		return dao.changeNickname(userId, unickname) ? "success" : "fail";
	}
	
}
