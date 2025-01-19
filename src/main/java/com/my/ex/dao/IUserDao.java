package com.my.ex.dao;

import java.util.List;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;

public interface IUserDao {
	int join(UserDto dto);
	UserDto login(String userId, String userPw);
	String getUserNickname(String userId);
	List<BoardDto> getUserPosts(String userId);
	List<BoardDto> getUserComments(String userId);
	List<BoardDto> getUserLikedPosts(String userId);
	int isOldPasswordCorrect(String userId, String oldPassword);
	boolean updatePassword(String userId, String newPassword);
}
