package com.my.ex.service;

import com.my.ex.dto.MessageDto;

public interface IMessageService {
	void saveMessage(MessageDto message);
	void getPastMessages(String sender, String receiver);
}
