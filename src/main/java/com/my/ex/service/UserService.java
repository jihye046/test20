package com.my.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.ex.dao.UserDao;
import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserDao dao;

	@Override
	public boolean join(UserDto dto) {
		int result = dao.join(dto);
		if(result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean login(String userId, String userPw) {
		UserDto dto = dao.login(userId, userPw);
		if(dto != null) {
			return true;
		}
		return false;
	}

}
