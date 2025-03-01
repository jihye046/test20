package com.my.ex.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.ex.dto.LikeDto;

@Repository
public class LikeDao implements ILikeDao {
	
	private final String NAMESPACE = "com.my.ex.LikeMapper.";
	
	@Autowired
	private SqlSession session;

	@Override
	public void addLike(LikeDto dto) {
		session.insert(NAMESPACE + "addLike", dto);
	}

	@Override
	public void removeLike(LikeDto dto) {
		session.delete(NAMESPACE + "removeLike", dto);
	}

	@Override
	public int isLiked(int bId, String userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bId", bId);
		map.put("userId", userId);
		return session.selectOne(NAMESPACE + "isLiked", map);
	}

	@Override
	public void addRecommend(LikeDto dto) {
		session.insert(NAMESPACE + "addRecommend", dto);
	}

	@Override
	public void removeRecommend(LikeDto dto) {
		session.delete(NAMESPACE + "removeRecommend", dto);
	}

	@Override
	public int isRecommended(int bId, String userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bId", bId);
		map.put("userId", userId);
		return session.selectOne(NAMESPACE + "isRecommended", map);
	}

}
