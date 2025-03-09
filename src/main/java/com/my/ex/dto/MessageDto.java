package com.my.ex.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MessageDto {
	private int messageId;
	private String code; // 상태코드
	private String sender; 
	private String receiver; 
	private String content; 
	private String userId;
	private String messageType;
	private Date regdate; 
	
}
