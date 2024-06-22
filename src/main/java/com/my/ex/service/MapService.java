package com.my.ex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.my.ex.dto.map.KakaoMapRequestDto;

public class MapService implements IMapService {
	
	@Autowired
	private KakaoMapRequestDto kakaoMap;
	
	// 사용자로부터 검색할 주소를 입력받아 좌표 얻어오기
	@Override
	public String getAddress(String type) {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(kakaoMap.getBaseurl() + "/" + type)
				.queryParam("Authorization", "KakaoAK " + kakaoMap.getRestKey())
				.queryParam("query", "구로구")
				.build();
		try {
			URL url = new URL(uriComponents.toString());
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			return readResponse(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String readResponse(HttpURLConnection connection) throws IOException {
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
	}

}
