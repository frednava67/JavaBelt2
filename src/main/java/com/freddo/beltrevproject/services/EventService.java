package com.freddo.beltrevproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.freddo.beltrevproject.models.Event;
import com.freddo.beltrevproject.repos.EventRepo;

@Service 
public class EventService { 
	// adding the event repo as a dependency 
	private final EventRepo eventRepo; 
	
	public EventService(EventRepo eventRepo) { 
	
	this.eventRepo = eventRepo; 
	
	} 
	
	// returns all the events
	public List<Event> allEvents() { 
	    return eventRepo.findAll(); 
	}
	
	public List<Event> stateEvents(String state) {
		System.out.println(state);
		return eventRepo.findByState(state);
	}
	
	public List<Event> notInStateEvents(String state) {
		List<Event> stateEvents = eventRepo.findByState(state);
		List<Event> outOfStateEvents = eventRepo.findAll();
		outOfStateEvents.removeAll(stateEvents);

		return outOfStateEvents;
	}
	
	// creates a event 
	public Event createEvent(Event event) { 
	    return eventRepo.save(event); 
	} 

	// updates an event 
	public Event updateEvent(Event event) { 
	    return eventRepo.save(event); 
	} 	
	
	//cancels an event
	
	public void deleteEvent(Event event) {
		eventRepo.delete(event);
		return;
	}
	
	// retrieves a event 
	public Event findEvent(Long id) { 
	    Optional<Event> optionalEvent = eventRepo.findById(id); 
	
	    if(optionalEvent.isPresent()) { 
	        return optionalEvent.get(); 
	    } else { 
	        return null; 
	    } 
	} 
}
