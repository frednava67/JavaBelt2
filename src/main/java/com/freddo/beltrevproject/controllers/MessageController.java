package com.freddo.beltrevproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.freddo.beltrevproject.models.Event;
import com.freddo.beltrevproject.models.Message;
import com.freddo.beltrevproject.models.User;
import com.freddo.beltrevproject.services.EventService;
import com.freddo.beltrevproject.services.MessageService;
import com.freddo.beltrevproject.services.UserService;

@Controller
public class MessageController {
	private final EventService eventService;
	private final UserService userService;
	private final MessageService messageService;

	
	public MessageController(EventService eventService, UserService userService, MessageService messageService) {
		this.eventService = eventService;
		this.userService = userService;
		this.messageService = messageService;
	}

	
	@RequestMapping(value="/messages/process", method=RequestMethod.POST)
    public String processMessage(@Valid @ModelAttribute("message") Message message, @RequestParam("eventid") Long eventid, BindingResult result, HttpSession session) {
        System.out.println("===============================================processMessage()");
        
        Event event = eventService.findEvent(eventid);
        Long  messagerId = (Long) session.getAttribute("userid");
        User messager = userService.findUserById(messagerId);
        message.setEvent(event);
        message.setMessager(messager);
        messageService.updateMessage(message);
                
        if(result.hasErrors()) {
        	System.out.println("1 =============================================processMessage()");
            return "/events/showEvent.jsp";
        }
    	System.out.println("2 =============================================processMessage()");
        return "redirect:/events/" + eventid;
    }
	
}
