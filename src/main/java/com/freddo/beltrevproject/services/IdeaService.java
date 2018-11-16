package com.freddo.beltrevproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.freddo.beltrevproject.models.Idea;
import com.freddo.beltrevproject.repos.IdeaRepo;

@Service 
public class IdeaService { 
	// adding the idea repo as a dependency 
	private final IdeaRepo ideaRepo; 

	public IdeaService(IdeaRepo ideaRepo) { 
		this.ideaRepo = ideaRepo; 
	}
	
	// returns all the ideas 
	public List<Idea> allIdeas() { 
	    return ideaRepo.findAll(); 
	} 
	
	// creates an idea 
	public Idea createIdea(Idea idea) { 
	    return ideaRepo.save(idea); 
	} 

	// updates an idea 
	public Idea updateIdea(Idea idea) { 
	    return ideaRepo.save(idea); 
	}
	
	public void deleteIdea(Idea idea) {
		ideaRepo.delete(idea);
	}
	
	// retrieves a idea 
	public Idea findIdea(Long id) { 
	    Optional<Idea> optionalIdea = ideaRepo.findById(id); 
	    if(optionalIdea.isPresent()) { 
	        return optionalIdea.get(); 
	    } else { 
	        return null; 
	    } 
	} 
}