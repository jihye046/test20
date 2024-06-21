package com.my.ex.dto.map;

import org.springframework.beans.factory.annotation.Value;

public class KakaoMap {
	
	@Value("${kakao.baseurl}")
	private String baseurl;
	
	@Value("${kakao.javaScript.key}")
	private String jsKey;
	
	@Value("${kakao.rest.key}")
	private String restKey;

	public KakaoMap() {}

	public KakaoMap(String baseurl, String jsKey, String restKey) {
		this.baseurl = baseurl;
		this.jsKey = jsKey;
		this.restKey = restKey;
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public String getJsKey() {
		return jsKey;
	}

	public void setJsKey(String jsKey) {
		this.jsKey = jsKey;
	}

	public String getRestKey() {
		return restKey;
	}

	public void setRestKey(String restKey) {
		this.restKey = restKey;
	}

	@Override
	public String toString() {
		return "kakaoMap [baseurl=" + baseurl + ", jsKey=" + jsKey + ", restKey=" + restKey + "]";
	}
	
}
