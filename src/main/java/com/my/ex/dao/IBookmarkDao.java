package com.my.ex.dao;

import com.my.ex.dto.BookmarkDto;

public interface IBookmarkDao {
	void addBookmark(BookmarkDto dto);
	void removeBookmark(BookmarkDto dto);
	int isBookmarked(int bId, String userId);
}
