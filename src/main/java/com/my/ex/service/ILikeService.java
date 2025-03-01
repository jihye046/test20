package com.my.ex.service;

import com.my.ex.dto.LikeDto;

public interface ILikeService {
	void addLike(LikeDto dto);
	void removeLike(LikeDto dto);
	boolean isLiked(int bId, String userId);
	void addRecommend(LikeDto dto);
	void removeRecommend(LikeDto dto);
	boolean isRecommended(int bId, String userId);
}
