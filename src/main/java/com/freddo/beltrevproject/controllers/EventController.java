package com.freddo.beltrevproject.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.freddo.beltrevproject.models.Event;
import com.freddo.beltrevproject.models.Message;
import com.freddo.beltrevproject.models.User;
import com.freddo.beltrevproject.services.EventService;
import com.freddo.beltrevproject.services.UserService;
import com.freddo.beltrevproject.validator.EventDateValidator;

@Controller
public class EventController {
	private final UserService userService;
	private final EventService eventService;
	private final EventDateValidator eventDateValidator;

	public EventController(UserService userService, EventService eventService, EventDateValidator eventDateValidator) {
		this.userService = userService;
		this.eventService = eventService;
		this.eventDateValidator = eventDateValidator;
	}

	@RequestMapping("/events")
	public String dashboard(@ModelAttribute("user") User user, @ModelAttribute("event") Event event, HttpSession session, Model model) {
        System.out.println("===============================================dashboard()");
		 Long loggedInUserId = (Long) session.getAttribute("userid");
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }	    
        
        User loggedInUser = userService.findUserById(loggedInUserId);
        model.addAttribute("user", loggedInUser);
        
        List<Event> inStateEvents = eventService.stateEvents(loggedInUser.getState());
        System.out.println(inStateEvents.size());
        model.addAttribute("inStateEvents", inStateEvents);

        List<Event> outOfStateEvents = eventService.notInStateEvents(loggedInUser.getState());
        model.addAttribute("outOfStateEvents", outOfStateEvents);
        
		return "/events/dashboardPage.jsp";
	}
	
	 @RequestMapping(value="/events/process", method=RequestMethod.POST)
	 public String processEvent(@RequestParam("hostid") Long hostid, @Valid @ModelAttribute("event") Event event, BindingResult result, Model model, HttpSession session) {
		System.out.println("===============================================processEvent()");
		eventDateValidator.validate(event, result);
		if(result.hasErrors()) {
			Long loggedInUserId = (Long) session.getAttribute("userid");
	        User loggedInUser = userService.findUserById(loggedInUserId);
	        model.addAttribute("user", loggedInUser);
	        
	        List<Event> inStateEvents = eventService.stateEvents(loggedInUser.getState());
	        System.out.println(inStateEvents.size());
	        model.addAttribute("inStateEvents", inStateEvents);

	        List<Event> outOfStateEvents = eventService.notInStateEvents(loggedInUser.getState());
	        model.addAttribute("outOfStateEvents", outOfStateEvents);
            return "/events/dashboardPage.jsp";
        }
        User host = userService.findUserById(hostid);
        event.setHost(host);
	    eventService.createEvent(event);
        return "redirect:/events";
    }

	 @RequestMapping("/events/{eventid}")
	 public String showEvent(@PathVariable("eventid") Long eventid, @ModelAttribute("message") Message message, HttpSession session, Model model ) {
		 System.out.println("===============================================showEvent()");

		 Long loggedInUserId = (Long) session.getAttribute("userid");
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }	
	        
		 Event event = eventService.findEvent(eventid);
		 List<Message> messages = event.getMessages();
		 
		 model.addAttribute("messages", messages);
		 model.addAttribute("event", event);
		 
		 return "/events/showEvent.jsp";
	 }	 
	 
	 
	 @RequestMapping("/events/{eventid}/edit")
	 public String editEvent(@PathVariable("eventid") Long eventid, HttpSession session, Model model ) {

		 Long loggedInUserId = (Long) session.getAttribute("userid");
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }		 
		 
		 Event event = eventService.findEvent(eventid);
		 
		 String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		 String fmteventDate = simpleDateFormat.format(event.getEventDate());
		 model.addAttribute("fmteventDate", fmteventDate);
		 		 		 
		 if (!(loggedInUserId.equals((Long) event.getHost().getId()))) {
			 return "redirect:/events";
		 }
		 
		 model.addAttribute("event", event);
		 
		 return "/events/editEvent.jsp";
	 }

	 @RequestMapping("/events/{eventid}/delete")
	 public String deleteEvent(@PathVariable("eventid") Long eventid, HttpSession session, Model model ) {
		 
		 Long loggedInUserId = (Long) session.getAttribute("userid");
		 if ((loggedInUserId.equals(null))) {
			 return "redirect:/";
		 }		 
		 
		 Event event = eventService.findEvent(eventid);
		 if (!(loggedInUserId.equals((Long) event.getHost().getId()))) {
			 return "redirect:/events";
		 }
		 
		 eventService.deleteEvent(event);
		 
		 return "redirect:/events";
	 }	 
	 
	 @RequestMapping(value="/events/edit/process", method=RequestMethod.POST)
	 public String processEvent(@RequestParam("eventName") String eventName,
			 					@RequestParam("eventDate") String eventDate,
			 					@RequestParam("eventLocation") String eventLocation,
			 					@RequestParam("eventState") String eventState,
			 					@RequestParam("eventId") Long eventId,
			 					@ModelAttribute("event") Event event, Model model, BindingResult result) {
		 System.out.println("===============================================processEvent()");
		event = eventService.findEvent(eventId);
		event.setName(eventName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = new Date();
		
		try {
			newDate = sdf.parse(eventDate);
		} 
			catch (Exception ex) {
			
		}
		
		event.setEventDate(newDate);
		event.setLocation(eventLocation);
		event.setState(eventState);
		eventDateValidator.validate(event, result);
		if(result.hasErrors()) {
			model.addAttribute("event", event);
		    return "/events/editEvent.jsp";
		}
		eventService.updateEvent(event);
		return "redirect:/events";
    }

	 @RequestMapping("/events/{eventid}/join/{userid}")
	 public String joinEvent(@PathVariable("eventid") Long eventid, @PathVariable("userid") Long userid, HttpSession session, Model model ) {

		 Long loggedInUserId = (Long) session.getAttribute("userid");
		 if ((loggedInUserId.equals(null))) {
			 return "redirect:/";
		 }

		 Event event = eventService.findEvent(eventid);		 
		 User user = userService.findUserById(loggedInUserId);
		 List<User> attendees = event.getUsers();
		 attendees.add(user);
		 event.setUsers(attendees);
		 eventService.updateEvent(event);
		 
		 return "redirect:/events";
	 }
	 
	 @RequestMapping("/events/{eventid}/unjoin/{userid}")
	 public String unjoinEvent(@PathVariable("eventid") Long eventid, @PathVariable("userid") Long userid, HttpSession session, Model model ) {

		 Long loggedInUserId = (Long) session.getAttribute("userid");
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }	

		 Event event = eventService.findEvent(eventid);
		 User user = userService.findUserById(loggedInUserId);
		 List<User> attendees = event.getUsers();
		 attendees.remove(user);
		 event.setUsers(attendees);
		 eventService.updateEvent(event);
		 
		 return "redirect:/events";
	 }


}	
	
	
