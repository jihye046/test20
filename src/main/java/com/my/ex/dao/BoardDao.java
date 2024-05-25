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
	public void updateLike(int bId, String bLike) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bId", bId);
		map.put("bLike", bLike);
		session.update(NAMESPACE + "updateLike", map);
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
		return session.selectList("pagingList", map);
	}
	
	@Override
	public List<BoardDto> sort_hitPagingList(HashMap<String, Object> map) {
		return session.selectList("sort_hitPagingList", map);
	}

	@Override
	public int boardCount() {
		return session.selectOne(NAMESPACE + "boardCount");
	}

	@Override
	public void updateCommentCount(int bGroup) {
		session.update(NAMESPACE + "updateCommentCount", bGroup);
	}

}
