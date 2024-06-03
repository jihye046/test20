package com.my.ex.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@PropertySource("classpath:config/naver.properties")
public class SocialLoginService {
	
	@Value("${naver.baseurl}")
	private String baseurl;
	
	@Value("${naver.response_type}")
	private String response_type;
	
	@Value("${naver.client_id}")
	private String client_id;
	
	@Value("${naver.state}")
	private String state;

	@Value("${naver.redirect_uri}")
	private String redirect_uri;
	
	public String getNaverAuthorizeUrl(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {
		System.out.println("ssssssssssssssss " + baseurl);
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(baseurl + "/" + type)
				.queryParam("response_type", response_type)
				.queryParam("client_id", client_id)
				.queryParam("state", URLEncoder.encode(state, "UTF-8"))
				.queryParam("redirect_uri", URLEncoder.encode(redirect_uri, "UTF-8"))
//				.fromHttpUrl("https://nid.naver.com/oauth2.0" + "/" + "authorize")
//				.queryParam("response_type", "code")
//				.queryParam("client_id", "1zSPuj67pCKKtQc5dyYn")
//				.queryParam("state", URLEncoder.encode("1234", "UTF-8"))
//				.queryParam("redirect_uri", URLEncoder.encode("http://localhost/user/naverCallback", "UTF-8"))
				.build();
		return uriComponents.toString();
	}
}
