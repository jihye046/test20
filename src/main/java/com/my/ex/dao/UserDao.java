package com.my.ex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.UserDto;

@Repository
public class UserDao implements IUserDao {
	
	private final String NAMESPACE = "com.my.ex.UserMapper.";
	
	@Autowired
	private SqlSession session;

	@Override
	public int join(UserDto dto) {
		return session.insert(NAMESPACE + "join", dto);
	}

	@Override
	public UserDto login(String userId, String userPw) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		return session.selectOne("login", map);
	}

	@Override
	public String getUserNickname(String userId) {
		return session.selectOne(NAMESPACE + "getUserNickname" , userId);
	}

	@Override
	public List<BoardDto> getUserPosts(String userId) {
		return session.selectList(NAMESPACE + "getUserPosts", userId);
	}

	@Override
	public List<BoardDto> getUserComments(String userId) {
		return session.selectList(NAMESPACE + "getUserComments", userId);
	}

	@Override
	public List<BoardDto> getUserLikedPosts(String userId) {
		return session.selectList(NAMESPACE + "getUserLikedPosts", userId);
	}

	@Override
	public int isOldPasswordCorrect(String userId, String oldPassword) {
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("oldPassword", oldPassword);
		return session.selectOne(NAMESPACE + "isOldPasswordCorrect", map);
	}

	@Override
	public String getCurrentPassword(String userId) {
		return session.selectOne(NAMESPACE + "getCurrentPassword", userId);
	}
	
	@Override
	public boolean updatePassword(String userId, String newPassword) {
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("newPassword", newPassword);
		int result = session.update(NAMESPACE + "updatePassword", map);
		return result > 0;
	}

	// 현재 프로필 정보 조회
	@Override
	public UserDto getCurrentProfile(String userId) {
		return session.selectOne(NAMESPACE + "getCurrentProfile", userId);
	}

	// 닉네임만 변경하는 경우
	@Override
	public int updateNickname(Map<String, String> map) {
		return session.update(NAMESPACE + "updateNickname", map);
	}

	// 프로필 이미지만 변경하는 경우
	@Override
	public int updateProfileImage(Map<String, String> map) {
		return session.update(NAMESPACE + "updateProfileImage", map);
	}

	// 닉네임 + 프로필 이미지 변경하는 경우
	@Override
	public int updateNicknameAndProfileImage(Map<String, String> map) {
		return session.update(NAMESPACE + "updateNicknameAndProfileImage", map);
	}

	// 댓글 프로필 이미지 가져오기
	@Override
	public String getProfileFilename(String bName) {
		return session.selectOne(NAMESPACE + "getProfileFilename" , bName);
	}

	// 아이디 찾기 - 사용자 정보 확인
	@Override
	public int checkUserInfoMatch(HashMap<String, String> map) {
		return session.selectOne(NAMESPACE + "checkUserInfoMatch", map);
	}

	// 아이디 찾기 - 인증 후 사용자정보 가져오기
	@Override
	public List<UserDto> findUserIdByEmail(HashMap<String, String> hashMap) {
		return session.selectList(NAMESPACE + "findUserIdByEmail", hashMap);
	}

}
