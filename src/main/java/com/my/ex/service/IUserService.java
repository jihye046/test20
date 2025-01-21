package com.my.ex.service;

import java.util.List;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;

public interface IUserService {
	boolean join(UserDto dto);
	boolean login(String userId, String userPw);
	String getUserNickname(String userId);
	List<BoardDto> getUserPosts(String userId);
	List<BoardDto> getUserComments(String userId);
	List<BoardDto> getUserLikedPosts(String userId);
	String checkCurrentPasswordAndChange(String userId, String oldPassword, String newPassword);
	String getCurrentNickname(String userId);
	String changeNickname(String userId, String unickname);
}
