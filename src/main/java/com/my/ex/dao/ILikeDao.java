package com.my.ex.dao;

import com.my.ex.dto.LikeDto;

public interface ILikeDao {
	void addLike(LikeDto dto);
	void removeLike(LikeDto dto);
	int isLiked(int bId, String userId);
	void addRecommend(LikeDto dto);
	void removeRecommend(LikeDto dto);
	int isRecommended(int bId, String userId);
}
