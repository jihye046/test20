package com.my.ex.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.my.ex.dao.SocialDao;
import com.my.ex.dto.NaverCallbackDto;
import com.my.ex.dto.NaverDto;
import com.my.ex.dto.NaverToken;
import com.my.ex.dto.SocialDto;

@Service
public class SocialService implements ISocialService {
	
	@Autowired
	private SocialDao dao;
	
	@Autowired
	private SocialDto dto;
	
	// 네이버 로그인 연동 URL 생성
	@Override
	public String getNaverAuthorizeUrl(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(dto.getBaseurl() + "/" + type)
				.queryParam("response_type", dto.getResponse_type())
				.queryParam("client_id", dto.getClient_id())
				.queryParam("state", URLEncoder.encode(dto.getState(), "UTF-8"))
				.queryParam("redirect_uri", URLEncoder.encode(dto.getRedirect_uri(), "UTF-8"))
				.build();
		return uriComponents.toString();
	}

	// accessToken 발급 요청
	@Override
	public String getNaverTokenUrl(String type, String grant_type, NaverCallbackDto naverCallbackDto) throws URISyntaxException,
																											 MalformedURLException,
																										 	 UnsupportedEncodingException {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(dto.getBaseurl() + "/" + type)
				.queryParam("grant_type", grant_type)
				.queryParam("client_id", dto.getClient_id())
				.queryParam("client_secret", dto.getClient_secret())
				.queryParam("code", naverCallbackDto.getCallbackCode())
				.queryParam("state", naverCallbackDto.getCallbackState())
				.build();
		
		try {
			URL url = new URL(uriComponents.toString());
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			
			int responseCode = connection.getResponseCode();
			BufferedReader br;
			
			br = (responseCode == 200) 
				? new BufferedReader(new InputStreamReader(connection.getInputStream()))
				: new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			
			String inputLine;
			StringBuffer bf = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				bf.append(inputLine);
			}
			br.close();
			return bf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// accessToken 이용하여 프로필 API 호출
	@Override
	public String getNaverUserByToken(NaverToken token) {
		try {
			URL url = new URL("https://openapi.naver.com/v1/nid/me");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", token.getToken_type() + " " + token.getAccess_token());
			
			int responseCode = connection.getResponseCode();
			BufferedReader br;
			
			br = (responseCode == 200) 
				? new BufferedReader(new InputStreamReader(connection.getInputStream()))
				: new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			
			String inputLine;
			StringBuffer bf = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				bf.append(inputLine);
			}
			br.close();
			return bf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkNaverIdExist(String sns_id) {
		int result = dao.checkNaverIdExist(sns_id);
		return (result > 0) ? true : false;
	}

	@Override
	public void socialJoin(NaverDto dto) {
		dao.socialJoin(dto);
	}
}
