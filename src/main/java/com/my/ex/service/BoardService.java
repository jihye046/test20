package com.my.ex.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.ex.dao.BoardDao;
import com.my.ex.dto.BoardDto;
import com.my.ex.dto.BoardPagingDto;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private BoardDao dao;
	
	@Override
	public boolean createBoard(BoardDto dto) {
		int result = dao.createBoard(dto);
		return (result > 0) ? true : false;
	}

	@Override
	public List<BoardDto> findAll() {
		return dao.findAll();
	}

	@Override
	public BoardDto detailBoard(int bId) {
		return dao.detailBoard(bId);
	}

	@Override
	public boolean updateBoard(BoardDto dto) {
		int result = dao.updateBoard(dto);
		return (result > 0) ? true : false;
	}

	@Override
	public boolean deleteBoard(int bId) {
		int result = dao.deleteBoard(bId);
		return (result > 0) ? true : false;
	}

	@Override
	public void updateHitCount(int bId) {
		dao.updateHitCount(bId);
	}
	
	@Override
	public int incrementLikesCount(int bId) {
		return dao.incrementLikesCount(bId);
	}
	
	@Override
	public int decrementLikesCount(int bId) {
		return dao.decrementLikesCount(bId);
	}
	
	@Override
	public int getTotalLikes(int bId) {
		return dao.getTotalLikes(bId);
	}

	@Override
	public void replyInsert(BoardDto dto) {
		dao.replyInsert(dto);
	}

	@Override
	public List<BoardDto> replyList(int bGroup) {
		return dao.replyList(bGroup);
	}

	@Override
	public void replyChildInsert(BoardDto dto) {
		dao.replyChildInsert(dto);
	}

	@Override
	public List<BoardDto> replyChildList(int bGruop) {
		return null;
	}

	@Override
	public List<BoardDto> pagingList(int page, String searchGubun, String searchText, String sortType) {
//		int pagingEnd = page * 10;
		int pagingEnd = page * 12;
//		int pagingStart = pagingEnd - 9;
		int pagingStart = pagingEnd - 11;
		HashMap<String, Object> map = new HashMap<>();
		map.put("pagingStart", pagingStart);
		map.put("pagingEnd", pagingEnd);
		map.put("searchGubun", searchGubun);
		map.put("searchText", searchText);
		map.put("sortType", sortType);
		return dao.pagingList(map);
	}
	
	@Override
	public List<BoardDto> sort_hitPagingList(int page, String searchGubun, String searchText) {
		int pagingEnd = page * 10;
		int pagingStart = pagingEnd - 9;
		HashMap<String, Object> map = new HashMap<>();
		map.put("pagingStart", pagingStart);
		map.put("pagingEnd", pagingEnd);
		map.put("searchGubun", searchGubun);
		map.put("searchText", searchText);
		return dao.sort_hitPagingList(map);
	}

	@Override
	public BoardPagingDto paingParam(int page) {
		// 한 페이지당 보여줄 게시글 개수
//		int pageLimit = 10;
		int pageLimit = 12;
		
		// 하단에 보여줄 페이지 [1],[2] ... 번호 개수
//		int blockLimit = 3;
		int blockLimit = 5;
		
		// 전체 글 개수 조회
		int boardCount = dao.boardCount();
		
		// 전체 페이지 개수 
		int maxPage = (int)(Math.ceil((double) boardCount / pageLimit));
		
		// 시작 페이지 값 계산
		int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
		
		// 끝 페이지 값 계산
		int endPage = startPage + blockLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		BoardPagingDto pageDto = new BoardPagingDto();
		pageDto.setPage(page);
		pageDto.setMaxPage(maxPage);
		pageDto.setStartPage(startPage);
		pageDto.setEndPage(endPage);
		return pageDto;
	}

	@Override
	public void updateCommentCount(int bGroup) {
		dao.updateCommentCount(bGroup);
	}

}
