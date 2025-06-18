package com.my.ex.service;

import java.util.HashMap;
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
	
	// 댓글 프로필 이미지 가져오기
	String getProfileFilename(String bName);
	
	// 아이디 찾기 - 사용자 정보 확인
	boolean checkUserInfoMatch(String userName, String uemail);
	List<UserDto> findUserIdByEmail(HashMap<String, String> hashMap);
}
