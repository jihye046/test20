package com.my.ex.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.ex.service.MailSendService;

@RestController
@RequestMapping("/userMail")
public class MailController {
	
	@Autowired
	private MailSendService mailService;
	private String authNum = "";
	
	@RequestMapping(value= "/send", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
	public ResponseEntity<String> sendMail(@RequestParam("uemail")String uemail){
		HashMap<String, String> map = mailService.joinEmail(uemail);
		authNum = map.get("authNum");
		return ResponseEntity.ok(map.get("message"));
	}
	
	@RequestMapping(value= "/check", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
	public ResponseEntity<String> emailCheck(@RequestParam("userCode")String userCode){
		String result = (userCode.equals(authNum)) ? "인증성공" : "인증실패";
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
}
