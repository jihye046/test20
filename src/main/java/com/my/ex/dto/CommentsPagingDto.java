package com.my.ex.dto;

public class CommentsPagingDto {
	private int page; // 현재 페이지
	private int maxPage; // 전체 블록 개수
	private int startPage; // 시작 블록 값
	private int endPage; // 끝 블록 값
	
	public CommentsPagingDto() {}

	public CommentsPagingDto(int page, int maxPage, int startPage, int endPage) {
		this.page = page;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "CommentsPagingDto [page=" + page + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}

}
