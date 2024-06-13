package com.my.ex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.ex.dto.naver.NaverDto;

@Repository
public class SocialDao implements ISocialDao {
	
	private final String NAMESPACE = "com.my.ex.SocialMapper.";
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int checkNaverIdExist(String sns_id) {
		return session.selectOne(NAMESPACE + "checkNaverIdExist", sns_id);
	}

	@Override
	public void socialJoin(NaverDto dto) {
		session.insert(NAMESPACE + "socialJoin", dto);
	}
}
