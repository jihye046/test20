package com.my.ex.service;

import com.my.ex.dto.BookmarkDto;

public interface IBookmarkService {
	void addBookmark(BookmarkDto dto);
	void removeBookmark(BookmarkDto dto);
	boolean isBookmarked(int bId, String userId);
}
