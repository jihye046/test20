package com.my.ex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.ex.dto.SocialDto;

@Repository
public class SocialDao implements ISocialDao {
	
	private final String NAMESPACE = "com.my.ex.SocialMapper.";
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int checkSocialIdExist(String sns_id) {
		return session.selectOne(NAMESPACE + "checkNaverIdExist", sns_id);
	}

	@Override
	public void socialJoin(SocialDto dto) {
		session.insert(NAMESPACE + "socialJoin", dto);
	}
}
