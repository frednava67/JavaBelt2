package com.freddo.beltrevproject.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.freddo.beltrevproject.models.Idea;

@Repository 
public interface IdeaRepo extends CrudRepository<Idea, Long>{ 
	List<Idea> findAll(); 
}
