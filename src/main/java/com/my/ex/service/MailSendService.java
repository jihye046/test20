package com.my.ex.service;

import java.util.HashMap;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/email.properties")
public class MailSendService {

	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authNum;
	
	@Value("${email.username}")
	private String emailUsername;
	
	public void makeRandomNum() {
		Random random = new Random();
		int min = 111111;
		int max = 999999;
		authNum = random.nextInt(max - min + 1) + min;
	}
	
	// 이메일 발송 양식
	public HashMap<String, String> joinEmail(String uemail) {
		makeRandomNum();
		String setFrom = emailUsername; 
		String toMail = uemail;
		String title = "[회원가입 인증 이메일]"; 
		String content = "인증 번호는 <h1>" + authNum + "</h1>입니다.</br>해당 인증번호를 기입해주세요.";
		boolean result = mailSend(setFrom, toMail, title, content);
		String message = "";
		if(result == true) {
			message = "success";
		} else {
			message = "fail";
		}
		
		HashMap<String, String> map = new HashMap<>();
		map.put("message", message);
		map.put("authNum", Integer.toString(authNum));
		return map;
	}
	
	// 이메일 전송
	public boolean mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content,true);
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
