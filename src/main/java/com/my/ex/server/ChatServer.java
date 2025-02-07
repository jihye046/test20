package com.my.ex.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.my.ex.dto.Message;

@ServerEndpoint("/chatServer")
public class ChatServer {
	
	// 각각의 상태 관리를 위해 클라이언트들의 세션을 리스트에 담아 관리
	private static List<Session> sessionList = new ArrayList<>();

	// 연결이 성공적으로 이루어졌을 때 처리
	@OnOpen
	public void handleOpen(Session session) {
		sessionList.add(session);
		checkSessionList(); // 접속자 확인
		clearSessionList();
	}
	

	// 클라이언트로부터 받은 메시지 처리
	@OnMessage
	public void handleMessage(String msg, Session session) {
		System.out.println(msg);
		
		// JSON 문자열을 Java 객체로 변환
//		ObjectMapper mapper = new ObjectMapper();
//		Message message = mapper.readValue(msg, Message.class);
		
		Gson gson = new Gson();
		Message message = gson.fromJson(msg, Message.class);
		
		if(message.getCode().equals("1")) { // 새로운 유저일 때
			for(Session s : sessionList) {
				if(s != session) { // 새로운 유저를 제외한 나머지 클라이언트
					try {
						s.getBasicRemote().sendText(msg); // getBasicRemote(): 세션과 관련된 소켓을 반환, sendText(): 클라이언트에게 메시지를 보냄
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else if(message.getCode().equals("2")) { // 기존 유저가 나감
			sessionList.remove(session);
			for(Session s : sessionList) {
				try {
					s.getBasicRemote().sendText(msg);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} else if(message.getCode().equals("3")) {
			// 보낸 사람빼고 나머지 사람에게 전달
			for(Session s : sessionList) {
				if(s != session) {
					try {
						s.getBasicRemote().sendText(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else if(message.getCode().equals("4")) {
			for(Session s : sessionList) {
				if(s != session) {
					try {
						s.getBasicRemote().sendText(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	//접속자를 확인하는 메서드
	private void checkSessionList() {
		System.out.println();
		System.out.println("[Session List]");
		for (Session session : sessionList) {
			System.out.println(session.getId());
		}
		System.out.println();
	}
	
	// 연결이 끊어진 세션이 있으면 세션리스트에서 제거
	private void clearSessionList() {
		Iterator<Session> iterator = sessionList.iterator();
		while(iterator.hasNext()) {
			if(!(iterator.next()).isOpen()) {
				iterator.remove();
			}
		}
	}
}
