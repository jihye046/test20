package com.my.ex.dao;

import java.util.List;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;

public interface IUserDao {
	int join(UserDto dto);
	UserDto login(String userId, String userPw);
	String getUserNickname(String userId);
	List<BoardDto> getUserPosts(String userId);
}
