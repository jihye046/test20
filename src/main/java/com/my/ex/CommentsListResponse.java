package com.my.ex;

import java.util.List;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.CommentsPagingDto;

public class CommentsListResponse {
	List<BoardDto> commentsPagingList;
	CommentsPagingDto commentsPagingDto;
	
	public CommentsListResponse(List<BoardDto> commentsPagingList, CommentsPagingDto commentsPagingDto) {
		this.commentsPagingList = commentsPagingList;
		this.commentsPagingDto = commentsPagingDto;
	}

	public List<BoardDto> getCommentsPagingList() {
		return commentsPagingList;
	}

	public void setCommentsPagingList(List<BoardDto> commentsPagingList) {
		this.commentsPagingList = commentsPagingList;
	}

	public CommentsPagingDto getCommentsPagingDto() {
		return commentsPagingDto;
	}

	public void setCommentsPagingDto(CommentsPagingDto commentsPagingDto) {
		this.commentsPagingDto = commentsPagingDto;
	}

	@Override
	public String toString() {
		return "CommentsListResponse [commentsPagingList=" + commentsPagingList + ", commentsPagingDto="
				+ commentsPagingDto + "]";
	}
	
}
