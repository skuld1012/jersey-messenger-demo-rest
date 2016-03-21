package me.tedzhang.demo.java.messenger.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.tedzhang.demo.java.messenger.database.DBHandler;
import me.tedzhang.demo.java.messenger.exception.DataNotFoundException;
import me.tedzhang.demo.java.messenger.model.Message;

public class MessageService {
	
	private static Map<Long, Message> messages = DBHandler.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello world!", "ted zhang"));
		messages.put(2L, new Message(2, "Hellow Jersey", "ted zhang"));
	}
	
	public List<Message> listAllMessages() {
		
		return new LinkedList<>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new LinkedList<>();
		for(Message message : messages.values()) {
			if(message.getDate().getYear() == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> messagesPaginated = new LinkedList<>(messages.values());
		if(start + size > messagesPaginated.size()) return new LinkedList<>();
		return messagesPaginated.subList(start, start + size);
	}
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("message with id " + id + " not found");
		}
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		
		if(message.getId() <= 0) {
			return null;
		} else {
			messages.put(message.getId(), message);
		}
		return message;
	}
	
	public Message removeMessage(long id) {
		
		return messages.remove(id);
	}
}
