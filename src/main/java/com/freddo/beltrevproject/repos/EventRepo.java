package com.freddo.beltrevproject.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.freddo.beltrevproject.models.Event;

@Repository 

public interface EventRepo extends CrudRepository<Event, Long>{ 
	List<Event> findAll();
	List<Event> findByState(String state);
}
