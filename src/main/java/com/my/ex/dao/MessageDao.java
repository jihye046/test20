package com.my.ex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.ex.dto.MessageDto;

@Repository
public class MessageDao implements IMessageDao {

	private final String NAMESPACE = "com.my.ex.MessageMapper.";

	@Autowired
	private SqlSession session;
	
	@Override
	public void saveMessage(MessageDto dto) {
		session.insert(NAMESPACE + "saveMessages", dto);
	}

	@Override
	public List<MessageDto> getPastMessages(int messageId) {
		return session.selectList(NAMESPACE + "getPastMessages", messageId);
	}

}
