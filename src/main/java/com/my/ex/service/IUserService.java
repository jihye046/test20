package com.my.ex.service;

import com.my.ex.dto.UserDto;

public interface IUserService {
	boolean join(UserDto dto);
	boolean login(String userId, String userPw);
}
