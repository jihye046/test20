package com.my.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.ex.dao.BookmarkDao;
import com.my.ex.dto.BookmarkDto;

@Service
public class BookmarkService implements IBookmarkService {
	
	@Autowired
	private BookmarkDao dao;

	@Override
	public void addBookmark(BookmarkDto dto) {
		dao.addBookmark(dto);
	}

	@Override
	public void removeBookmark(BookmarkDto dto) {
		dao.removeBookmark(dto);
	}

	@Override
	public boolean isBookmarked(int bId, String userId) {
		int result = dao.isBookmarked(bId, userId);
		return result > 0;
	}

}
