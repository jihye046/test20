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
		String title = "[HomeTalk] 회원가입 인증 이메일";
		String boxStyle = "width:250px; height:60px; border:1.5px solid Tomato; border-radius:8px; " +
                		  "display:flex; justify-content:center; " +
                		  "align-items:center; font-size:20px; font-weight:500; color:Tomato;";
		String content = "<h2>HomeTalk 회원가입 인증문자입니다.</h2><br><br>" +
                		 "<p>HomeTalk에 가입하신 것을 환영합니다.</p>" +
                		 "<p>아래의 인증코드를 입력하시면 가입이 정상적으로 완료됩니다.</p>" +
						 "<div style=\"" + boxStyle + "\">" + authNum + "</div>";
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
