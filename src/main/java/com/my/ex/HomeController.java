package com.my.ex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import com.my.ex.dto.BoardDto;
import com.my.ex.dto.BoardPagingDto;
import com.my.ex.service.BoardService;

@Controller
public class HomeController {

	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
									@RequestParam(value = "searchGubun", required = false, defaultValue = "") String searchGubun,
									@RequestParam(value = "searchText", required = false, defaultValue = "") String searchText,
									@RequestParam(value = "sortType", required = false, defaultValue = "latest") String sortType) {
		List<BoardDto> pagingList = service.pagingList(page, searchGubun, searchText, sortType);
		BoardPagingDto pageDto = service.paingParam(page);
		for(BoardDto dto : pagingList) {
			// HTML 이스케이프 처리
			String escapedContent = HtmlUtils.htmlEscape(dto.getbContent());
			dto.setbContent(escapedContent);
		}
		model.addAttribute("boardList", pagingList);
		model.addAttribute("paging", pageDto);
		return "/board/pagingList";
	}
	
}