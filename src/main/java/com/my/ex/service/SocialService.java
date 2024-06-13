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
import com.my.ex.dto.google.GoogleCallbackDto;
import com.my.ex.dto.google.GoogleLoginRequestDto;
import com.my.ex.dto.google.GoogleToken;
import com.my.ex.dto.naver.NaverCallbackDto;
import com.my.ex.dto.naver.NaverDto;
import com.my.ex.dto.naver.NaverLoginRequestDto;
import com.my.ex.dto.naver.NaverToken;

@Service
public class SocialService implements ISocialService {
	
	@Autowired
	private SocialDao dao;
	
	@Autowired
	private NaverLoginRequestDto naverLoginRequestDto;
	
	@Autowired
	private GoogleLoginRequestDto googleLoginRequestDto;
	
	// 네이버 로그인 연동 URL 생성
	@Override
	public String getNaverAuthorizeUrl(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(naverLoginRequestDto.getBaseurl() + "/" + type)
				.queryParam("response_type", naverLoginRequestDto.getResponse_type())
				.queryParam("client_id", naverLoginRequestDto.getClient_id())
				.queryParam("state", URLEncoder.encode(naverLoginRequestDto.getState(), "UTF-8"))
				.queryParam("redirect_uri", URLEncoder.encode(naverLoginRequestDto.getRedirect_uri(), "UTF-8"))
				.build();
		return uriComponents.toString();
	}

	// accessToken 발급 요청
	@Override
	public String getNaverTokenUrl(String type, String grant_type, NaverCallbackDto naverCallbackDto) throws URISyntaxException,
																											 MalformedURLException,
																										 	 UnsupportedEncodingException {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(naverLoginRequestDto.getBaseurl() + "/" + type)
				.queryParam("grant_type", grant_type)
				.queryParam("client_id", naverLoginRequestDto.getClient_id())
				.queryParam("client_secret", naverLoginRequestDto.getClient_secret())
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

	// 구글 로그인 연동 URL 생성
	@Override
	public String getGoogleAuthorizeUrl() throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(googleLoginRequestDto.getBaseurl())
				.queryParam("response_type", googleLoginRequestDto.getResponse_type())
				.queryParam("client_id", googleLoginRequestDto.getClient_id())
				.queryParam("scope", "email%20profile%20openid") // 구글 api에서 선택한 범위
				.queryParam("state", URLEncoder.encode(googleLoginRequestDto.getState(), "UTF-8"))
				.queryParam("access_type", "offline")
				.queryParam("redirect_uri", URLEncoder.encode(googleLoginRequestDto.getRedirect_uri(), "UTF-8"))
				.build();
		return uriComponents.toString();
	}

	// accessToken 발급 요청
	@Override
	public String getGoogleTokenUrl(String type, String grant_type, GoogleCallbackDto googleCallbackDto) throws URISyntaxException,
																										 		MalformedURLException,
																										 		UnsupportedEncodingException {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl("https://oauth2.googleapis.com/" + type)
				.queryParam("grant_type", grant_type)
				.queryParam("client_id", googleLoginRequestDto.getClient_id())
				.queryParam("client_secret", googleLoginRequestDto.getClient_secret())
				.queryParam("code", googleCallbackDto.getCallbackCode())
				.queryParam("state", googleCallbackDto.getCallbackState())
//				.queryParam("redirect_uri", URLEncoder.encode("/getGoogleUserInfo", "UTF-8"))
				.queryParam("redirect_uri", "/getGoogleUserInfo", "UTF-8")
				.build();
		System.out.println("grantType:" + grant_type);
		System.out.println("client_id:" + googleLoginRequestDto.getClient_id());
		System.out.println("client_pw:" + googleLoginRequestDto.getClient_secret());
		System.out.println("code:" + googleCallbackDto.getCallbackCode());
		System.out.println("state:" + googleCallbackDto.getCallbackState());
		
		// POST 요청에 필요한 본문 데이터 구성
	    String requestBody = uriComponents.getQuery();
		
		try {
			URL url = new URL(uriComponents.toString());
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			// POST 요청 시 필수 
			connection.setRequestMethod("POST");
			connection.setDoOutput(true); 
	        connection.setRequestProperty("Content-Length", String.valueOf(requestBody.length())); // Content-Length 헤더 설정
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.getOutputStream().write(requestBody.getBytes()); // 요청 본문 전송
	        
			int responseCode = connection.getResponseCode(); // error 발생 부분
			System.out.println("responseCode:" + responseCode);
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
	
	// accesstoken 주고 userInfo 받기
	@Override
	public String getGoogleUserByToken(GoogleToken token) {
		try {
			URL url = new URL("https://oauth2.googleapis.com/tokeninfo");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("id_token", token.getId_token());
			
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
}
