package com.my.ex.dao;

import java.util.HashMap;
import java.util.List;

import com.my.ex.dto.BoardDto;

public interface IBoardDao {
	int createBoard(BoardDto dto);
	List<BoardDto> findAll();
	BoardDto detailBoard(int bId);
	int updateBoard(BoardDto dto);
	int deleteBoard(int bId);
	void updateHitCount(int bId);
	
	// 게시글 좋아요
	int incrementLikesCount(int bId); 
	int decrementLikesCount(int bId);
	int getTotalLikes(int bId);
	
	// 북마크
	int incrementBookmarksCount(int bId); 
	int decrementBookmarksCount(int bId);
	int getTotalBookmarks(int bId);
	
	// 댓글 추천
	void incrementRecommendation(int bId);
	void decrementRecommendation(int bId);
	
	void replyInsert(BoardDto dto);
	void replyShape(int bGroup, int bStep);
	List<BoardDto> replyList(int bGroup);
	void replyChildInsert(BoardDto dto);
	List<BoardDto> replyChildList(int bGroup);
	int boardCount();
	int incrementCommentCount(int bGroup);
	int decrementCommentCount(int bGroup);
	
	// 게시글 페이징
	List<BoardDto> pagingList(HashMap<String, Object> map);
	List<BoardDto> sort_hitPagingList(HashMap<String, Object> map);
	
	//댓글 페이징
	List<BoardDto> commentsPagingList(HashMap<String, Object> map);
	List<BoardDto> comments_sort_likePagingList(HashMap<String, Object> map);
	int commentsCount(int bGroup);
}
