package com.freddo.beltrevproject.services;

import java.util.Comparator;
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

	// returns all the ideas likes desc
	public List<Idea> allIdeasLikersDesc() { 

		List<Idea> ideas = ideaRepo.findAll();
		
		ideas.sort(new Comparator<Idea>() {
		    @Override
		    public int compare(Idea i1, Idea i2) {
		        if(i1.getLikers().size() == i2.getLikers().size()){
		            return 0;
		        }
		        return i1.getLikers().size() > i2.getLikers().size() ? -1 : 1;
		     }
		});
		
	    return ideas;
	} 	

	// returns all the ideas likes asc
	public List<Idea> allIdeasLikersAsc() { 

		List<Idea> ideas = ideaRepo.findAll();
		
		ideas.sort(new Comparator<Idea>() {
		    @Override
		    public int compare(Idea i1, Idea i2) {
		        if(i1.getLikers().size() == i2.getLikers().size()){
		            return 0;
		        }
		        return i1.getLikers().size() < i2.getLikers().size() ? -1 : 1;
		     }
		});
		
	    return ideas;
	
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