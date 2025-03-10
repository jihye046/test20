package com.my.ex.dao;

import java.util.List;

import com.my.ex.dto.MessageDto;

public interface IMessageDao {
	void saveMessage(MessageDto dto);
	List<MessageDto> getPastMessages(int messageId);
}
