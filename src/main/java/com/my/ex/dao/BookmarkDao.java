package com.my.ex.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.ex.dto.BookmarkDto;

@Repository
public class BookmarkDao implements IBookmarkDao {
	
	private final String NAMESPACE = "com.my.ex.BookmarkMapper.";

	@Autowired
	private SqlSession session;
	
	@Override
	public void addBookmark(BookmarkDto dto) {
		session.insert(NAMESPACE + "addBookmark", dto);
	}

	@Override
	public void removeBookmark(BookmarkDto dto) {
		session.delete(NAMESPACE + "removeBookmark", dto);
	}

	@Override
	public int isBookmarked(int bId, String userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bId", bId);
		map.put("userId", userId);
		return session.selectOne(NAMESPACE + "isBookmarked", map);
	}

}
