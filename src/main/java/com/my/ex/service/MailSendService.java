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
		String title = "[HomeTalk] 본인 확인을 위한 인증 코드입니다.";
		String boxStyle = "width:250px; height:60px; border:1.5px solid Tomato; border-radius:8px; " +
                		  "display:flex; justify-content:center; " +
                		  "align-items:center; font-size:20px; font-weight:500; color:Tomato;";
		String content = "<h2>요청하신 인증을 위해 아래의 인증 코드를 입력해 주세요.</h2><br>" +
                		 "<p>이 인증 코드는 회원가입 또는 아이디/비밀번호 찾기를 위해 발송되었습니다.</p>" +
                		 "<p>아래의 인증코드를 입력하시면 다음 단계로 진행됩니다.</p>" +
						 "<div style=\"" + boxStyle + "\">" + authNum + "</div>" +
						 "<p style=\"margin-top:20px; font-size:14px; color:gray;\">" +
						 "※ 본인이 요청하지 않은 경우 이 메일은 무시하셔도 됩니다." +
						 "</p>";
		boolean result = mailSend(setFrom, toMail, title, content);
		String message = "";
		message = (result) ? "success" : "fail";
		
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
