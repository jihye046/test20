package com.my.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.ex.dao.LikeDao;
import com.my.ex.dto.LikeDto;

@Service
public class LikeService implements ILikeService{
	
	@Autowired
	private LikeDao dao;

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
	
}
