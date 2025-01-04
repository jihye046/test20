package com.my.ex.dao;

import java.util.HashMap;
import java.util.List;

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

}
