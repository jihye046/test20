package com.my.ex.dao;

import com.my.ex.dto.NaverDto;

public interface ISocialDao {
	int checkNaverIdExist(String sns_id);
	void socialJoin(NaverDto dto);
}
