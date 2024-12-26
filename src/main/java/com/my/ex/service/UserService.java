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
	
}
