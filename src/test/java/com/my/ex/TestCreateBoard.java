package com.my.ex;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.ex.dao.BoardDao;
import com.my.ex.dto.BoardDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
	{"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TestCreateBoard {
	
	@Autowired
	private BoardDao dao;
	
	@Test
	public void testCreateBoard() throws Exception {
		BoardDto dto = new BoardDto();
		int num = 1;
		for(int i = 0; i < 100; i++) {
			dto.setbName("홍길동" + num);
			dto.setbTitle("제목" + num);
			dto.setbContent("내용" + num);
			dao.createBoard(dto);
			num++;
			Thread.sleep(100);
		}
	}
}
