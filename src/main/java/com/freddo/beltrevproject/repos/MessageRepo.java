package com.freddo.beltrevproject.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.freddo.beltrevproject.models.Message;

@Repository 

public interface MessageRepo extends CrudRepository<Message, Long>{ 
	List<Message> findAll(); 
}