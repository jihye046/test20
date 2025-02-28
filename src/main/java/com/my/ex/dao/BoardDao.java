package com.my.ex.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.ex.dto.BoardDto;

@Repository
public class BoardDao implements IBoardDao {
	
	private final String NAMESPACE = "com.my.ex.BoardMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public int createBoard(BoardDto dto) {
		return session.insert(NAMESPACE + "createBoard", dto); 
	}

	@Override
	public List<BoardDto> findAll() {
		return session.selectList(NAMESPACE + "findAll");
	}

	@Override
	public BoardDto detailBoard(int bId) {
		return session.selectOne(NAMESPACE + "detailBoard", bId);
	}

	@Override
	public int updateBoard(BoardDto dto) {
		return session.update(NAMESPACE + "updateBoard", dto);
	}

	@Override
	public int deleteBoard(int bId) {
		return session.delete(NAMESPACE + "deleteBoard", bId);
	}

	@Override
	public void updateHitCount(int bId) {
		session.update(NAMESPACE + "updateHitCount", bId);
	}
	
	@Override
	public int incrementLikesCount(int bId) {
		return session.update(NAMESPACE + "incrementLikesCount", bId);
	}
	
	@Override
	public int decrementLikesCount(int bId) {
		return session.update(NAMESPACE + "decrementLikesCount", bId);
	}
	
	@Override
	public int getTotalLikes(int bId) {
		return session.selectOne(NAMESPACE + "getTotalLikes", bId);
	}
	
	@Override
	public int incrementBookmarksCount(int bId) {
		return session.update(NAMESPACE + "incrementBookmarksCount", bId);
	}

	@Override
	public int decrementBookmarksCount(int bId) {
		return session.update(NAMESPACE + "decrementBookmarksCount", bId);
	}

	@Override
	public int getTotalBookmarks(int bId) {
		return session.selectOne(NAMESPACE + "getTotalBookmarks", bId);
	}

	@Override
	public void replyInsert(BoardDto dto) {
		replyShape(dto.getbGroup(), dto.getbStep());
		session.insert(NAMESPACE + "replyInsert", dto);
	}

	@Override
	public void replyShape(int bGroup, int bStep) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bGroup", bGroup);
		map.put("bStep", bStep);
		session.update(NAMESPACE + "replyShape", map);
	}

	@Override
	public List<BoardDto> replyList(int bGroup) {
		return session.selectList(NAMESPACE + "replyList", bGroup);
	}

	@Override
	public void replyChildInsert(BoardDto dto) {
		replyShape(dto.getbGroup(), dto.getbStep());
		session.insert(NAMESPACE + "replyChildInsert", dto);
	}

	@Override
	public List<BoardDto> replyChildList(int bGroup) {
		return session.selectList(NAMESPACE + "replyChildList", bGroup);
	}

	@Override
	public List<BoardDto> pagingList(HashMap<String, Object> map) {
		return session.selectList(NAMESPACE + "pagingList", map);
	}
	
	@Override
	public List<BoardDto> sort_hitPagingList(HashMap<String, Object> map) {
		return session.selectList(NAMESPACE + "sort_hitPagingList", map);
	}
	
	@Override
	public List<BoardDto> commentsPagingList(HashMap<String, Object> map) {
		return session.selectList(NAMESPACE + "commentsPagingList", map);
	}

	@Override
	public List<BoardDto> comments_sort_likePagingList(HashMap<String, Object> map) {
		return session.selectList(NAMESPACE + "commentsPagingList", map);
	}

	@Override
	public int boardCount() {
		return session.selectOne(NAMESPACE + "boardCount");
	}

	@Override
	public int updateCommentCount(int bGroup) {
		session.update(NAMESPACE + "updateCommentCount", bGroup);
		return session.selectOne(NAMESPACE + "commentsCount", bGroup);
	}

	@Override
	public int commentsCount(int bGroup) {
		return session.selectOne(NAMESPACE + "commentsCount", bGroup);
	}

}
