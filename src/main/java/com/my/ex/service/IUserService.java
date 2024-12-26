package com.my.ex.service;

import java.util.List;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;

public interface IUserService {
	boolean join(UserDto dto);
	boolean login(String userId, String userPw);
	String getUserNickname(String userId);
	List<BoardDto> getUserPosts(String userId);
}
