package com.my.ex;

import java.util.List;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.CommentsPagingDto;

public class CommentsListResponse {
	List<BoardDto> commentsPagingList;
	CommentsPagingDto commentsPagingDto;
	List<String> profileImageUrls;
	
	public CommentsListResponse() {}

	public CommentsListResponse(List<BoardDto> commentsPagingList, CommentsPagingDto commentsPagingDto,
			List<String> profileImageUrls) {
		this.commentsPagingList = commentsPagingList;
		this.commentsPagingDto = commentsPagingDto;
		this.profileImageUrls = profileImageUrls;
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

	public List<String> getProfileImageUrls() {
		return profileImageUrls;
	}

	public void setProfileImageUrls(List<String> profileImageUrls) {
		this.profileImageUrls = profileImageUrls;
	}

	@Override
	public String toString() {
		return "CommentsListResponse [commentsPagingList=" + commentsPagingList + ", commentsPagingDto="
				+ commentsPagingDto + ", profileImageUrls=" + profileImageUrls + "]";
	}
	
}
