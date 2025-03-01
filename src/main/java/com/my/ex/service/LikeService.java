package com.my.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.ex.dao.BoardDao;
import com.my.ex.dao.LikeDao;
import com.my.ex.dto.LikeDto;

@Service
public class LikeService implements ILikeService{
	
	@Autowired
	private LikeDao dao;
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public void addLike(LikeDto dto) {
		dao.addLike(dto);
	}

	@Override
	public void removeLike(LikeDto dto) {
		dao.removeLike(dto);
	}

	@Override
	public boolean isLiked(int bId, String userId) {
		int result = dao.isLiked(bId, userId);
		return result > 0;
	}

	@Override
	public void addRecommend(LikeDto dto) {
		dao.addRecommend(dto);
	}

	@Override
	public void removeRecommend(LikeDto dto) {
		dao.removeRecommend(dto);
	}

	@Override
	public boolean isRecommended(int bId, String userId) {
		return dao.isRecommended(bId, userId) > 0;
	}
	
}
