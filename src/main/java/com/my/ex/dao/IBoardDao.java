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
	void updateLike(int bId, String bLike);
	void replyInsert(BoardDto dto);
	void replyShape(int bGroup, int bStep);
	List<BoardDto> replyList(int bGroup);
	void replyChildInsert(BoardDto dto);
	List<BoardDto> replyChildList(int bGroup);
	List<BoardDto> pagingList(HashMap<String, Object> map);
	List<BoardDto> sort_hitPagingList(HashMap<String, Object> map);
	int boardCount();
	void updateCommentCount(int bGroup);
}
