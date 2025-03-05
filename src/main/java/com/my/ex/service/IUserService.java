package com.my.ex.service;

import java.util.List;
import java.util.Map;

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
	
	// 프로필 이미지, 닉네임
	UserDto getCurrentProfile(String userId);
	boolean updateNickname(Map<String, String> map); 
	boolean updateProfileImage(Map<String, String> map); 
	boolean updateNicknameAndProfileImage(Map<String, String> map); 

//	boolean changeNickname(String userId, String unickname);
//	int updateProfile(String fileName);
}
