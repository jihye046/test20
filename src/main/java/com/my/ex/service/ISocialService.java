package com.my.ex.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.my.ex.dto.google.GoogleCallbackDto;
import com.my.ex.dto.google.GoogleToken;
import com.my.ex.dto.naver.NaverCallbackDto;
import com.my.ex.dto.naver.NaverDto;
import com.my.ex.dto.naver.NaverToken;

public interface ISocialService {
	String getNaverAuthorizeUrl(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String getNaverTokenUrl(String type, String grant_type, NaverCallbackDto naverCallbackDto) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String getNaverUserByToken(NaverToken token);
	boolean checkNaverIdExist(String sns_id);
	void socialJoin(NaverDto dto);
	String getGoogleAuthorizeUrl() throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String getGoogleTokenUrl(String type, String grant_type, GoogleCallbackDto googleCallbackDto) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	String getGoogleUserByToken(GoogleToken token);
}
