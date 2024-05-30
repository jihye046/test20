package com.my.ex.dto;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

@PropertySource("classpath:config/email.properties")
public class NaverLoginDto {
	@Value("${naver.client_id}")
	private static String CLIENT_ID;
	@Value("${naver.client_pw}")
	private static String CLIENT_PW;
	@Value("${naver.redirect_url}") // 인증결과 받을 callback url 
	private static String REDIRECT_URL;
	private final static String SESSION_STATE = "oauth_state"; // 애플리케이션이 생성한 상태 토큰
	
	public String getAuthorizationUrl(HttpSession session) {
		String state = generateRandomString();
		setSession(session,state);
//		OAuth20Service oauthService = new ServiceBuilder()
//		.apiKey(CLIENT_ID)
//		.apiSecret(CLIENT_PW)
//		.callback(REDIRECT_URL)
//		.build(Naver)
		return "";
	}

	private void setSession(HttpSession session, String state) {
		// TODO Auto-generated method stub
		
	}

	private String generateRandomString() {
		// TODO Auto-generated method stub
		return null;
	}
}
