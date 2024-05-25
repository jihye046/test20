package com.my.ex.service;

import java.util.List;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.BoardPagingDto;

public interface IBoardService {
	boolean createBoard(BoardDto dto);
	List<BoardDto> findAll();
	BoardDto detailBoard(int bId);
	boolean updateBoard(BoardDto dto);
	boolean deleteBoard(int bId);
	void updateHitCount(int bId);
	BoardDto updateLike(int bId, String bLike);
	void replyInsert(BoardDto dto);
	List<BoardDto> replyList(int bGroup);
	void replyChildInsert(BoardDto dto);
	List<BoardDto> replyChildList(int bGruop);
	List<BoardDto> pagingList(int page, String searchGubun, String searchText, String sortType);
	List<BoardDto> sort_hitPagingList(int page, String searchGubun, String searchText);
	BoardPagingDto paingParam(int page); // 페이징처리 시 하단에 보일 번호
	void updateCommentCount(int bGroup);
}
