package com.my.ex.dao;

import com.my.ex.dto.SocialDto;

public interface ISocialDao {
	int checkSocialIdExist(String sns_id);
	void socialJoin(SocialDto dto);
}
