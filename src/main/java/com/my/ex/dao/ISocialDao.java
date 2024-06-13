package com.my.ex.dao;

import com.my.ex.dto.naver.NaverDto;

public interface ISocialDao {
	int checkNaverIdExist(String sns_id);
	void socialJoin(NaverDto dto);
}
