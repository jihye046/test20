package com.my.ex.service;

import java.util.HashMap;
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

	@Override
	public String checkCurrentPasswordAndChange(String userId, String oldPassword, String newPassword) {
		int isOldPasswordCorrectResult = dao.isOldPasswordCorrect(userId, oldPassword);
		if(isOldPasswordCorrectResult == 1) { // 현재 비밀번호와 일치한다면
			if(dao.updatePassword(userId, newPassword)) { // 업데이트를 실행
				return "success"; // 업데이트가 성공한 경우
			} else {
				return "fail"; // 업데이트가 실패한 경우
			}
		}
		return "oldPasswordIncorrect"; // 현재 비밀번호가 일치하지 않는 경우
	}
	
}
