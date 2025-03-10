package com.my.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.ex.dao.MessageDao;
import com.my.ex.dto.MessageDto;

@Service
public class MessageService implements IMessageService {
	
	@Autowired
	private MessageDao dao;

	@Override
	public void saveMessage(MessageDto dto) {
		dao.saveMessage(dto);
	}

	@Override
	public List<MessageDto> getPastMessages(int messageId) {
		return dao.getPastMessages(messageId);
	}
	
}
