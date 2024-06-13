package com.my.ex.dao;

import com.my.ex.dto.UserDto;

public interface IUserDao {
	int join(UserDto dto);
	UserDto login(String userId, String userPw);
	String getUserNickname(String userId);
}
