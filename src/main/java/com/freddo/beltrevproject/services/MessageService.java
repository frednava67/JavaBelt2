package com.freddo.beltrevproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.freddo.beltrevproject.models.Message;
import com.freddo.beltrevproject.repos.MessageRepo;

@Service 
public class MessageService { 
	// adding the message repo as a dependency 
	private final MessageRepo messageRepo; 
	
	public MessageService(MessageRepo messageRepo) { 
	
	this.messageRepo = messageRepo; 
	
	} 
	
	// returns all the messages
	public List<Message> allMessages() { 
	    return messageRepo.findAll(); 
	} 
	
	// creates a message 
	public Message createMessage(Message message) { 
	    return messageRepo.save(message); 
	} 
	
	// updates a message 
	public Message updateMessage(Message message) { 
	    return messageRepo.save(message); 
	} 
	
	// retrieves a message 
	public Message findMessage(Long id) { 
	    Optional<Message> optionalMessage = messageRepo.findById(id); 
	
	    if(optionalMessage.isPresent()) { 
	        return optionalMessage.get(); 
	    } else { 
	        return null; 
	    } 
	} 
}
