package com.my.ex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.my.ex.dto.map.NaverMapRequestDto;

@Service
public class MapService implements IMapService {
	
	@Autowired
	private NaverMapRequestDto dto;
	
	// 사용자로부터 검색할 주소를 입력받아 좌표 얻어오기
	@Override
	public String getAddress(String type) {
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl(type)
				.queryParam("start", "126.9780,37.5665") // 예제 시작점 (서울)
				.queryParam("goal", "126.7052,37.4563") // 예제 목표점 (인천)
				.queryParam("option", "trafast")
				.build();
		try {
			URL url = new URL(uriComponents.toString());
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", dto.getClient_id());
			connection.setRequestProperty("X-NCP-APIGW-API-KEY", dto.getClient_secret());
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
