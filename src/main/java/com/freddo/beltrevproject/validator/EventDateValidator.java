package com.freddo.beltrevproject.validator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.freddo.beltrevproject.models.Event;

@Component
public class EventDateValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        
        if (event == null) {
        	errors.rejectValue("eventObject", "Null");
        	return;
        }
        
        Date currentDate = new Date();
        
        if (event.getEventDate() != null) {
            if (event.getEventDate().before(currentDate)) {
                errors.rejectValue("eventDate", "Future");
            }     
        }
    }
}