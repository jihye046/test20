package com.my.ex.service;

import java.util.List;

import com.my.ex.dto.MessageDto;

public interface IMessageService {
	void saveMessage(MessageDto dto);
	List<MessageDto> getPastMessages(int messageId);
}
