package com.my.ex;

import java.util.List;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.BoardPagingDto;

public class SortResponse {
	List<BoardDto> sort_hitPagingList;
	BoardPagingDto pageDto;
	
	public SortResponse() {}
	
	public SortResponse(List<BoardDto> sort_hitPagingList, BoardPagingDto pageDto) {
		this.sort_hitPagingList = sort_hitPagingList;
		this.pageDto = pageDto;
	}

	public List<BoardDto> getSort_hitPagingList() {
		return sort_hitPagingList;
	}

	public void setSort_hitPagingList(List<BoardDto> sort_hitPagingList) {
		this.sort_hitPagingList = sort_hitPagingList;
	}

	public BoardPagingDto getPageDto() {
		return pageDto;
	}

	public void setPageDto(BoardPagingDto pageDto) {
		this.pageDto = pageDto;
	}

}
