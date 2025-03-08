package com.my.ex.dao;

import java.util.List;
import java.util.Map;

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
	String getCurrentPassword(String userId);
	boolean updatePassword(String userId, String newPassword);
	
	// 프로필 이미지, 닉네임
	UserDto getCurrentProfile(String userId);
	int updateNickname(Map<String, String> map); 
	int updateProfileImage(Map<String, String> map); 
	int updateNicknameAndProfileImage(Map<String, String> map);
	
	// 댓글 프로필 이미지 가져오기
	String getProfileFilename(String bName);
}
