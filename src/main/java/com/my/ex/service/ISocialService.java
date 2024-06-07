package com.my.ex.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.my.ex.dto.NaverCallbackDto;
import com.my.ex.dto.NaverDto;
import com.my.ex.dto.NaverToken;

public interface ISocialService {
	String getNaverAuthorizeUrl(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String getNaverTokenUrl(String type, String grant_type, NaverCallbackDto naverCallbackDto) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String getNaverUserByToken(NaverToken token);
	boolean checkNaverIdExist(String sns_id);
	void socialJoin(NaverDto dto);
}
