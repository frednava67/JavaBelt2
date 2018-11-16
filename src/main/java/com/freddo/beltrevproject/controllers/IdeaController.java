package com.freddo.beltrevproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.freddo.beltrevproject.models.Idea;
import com.freddo.beltrevproject.models.User;
import com.freddo.beltrevproject.services.IdeaService;
import com.freddo.beltrevproject.services.UserService;

@Controller 
public class IdeaController { 
	private final IdeaService ideaService; 
	private final UserService userService; 

	public IdeaController(IdeaService ideaService, UserService userService) { 
		this.ideaService = ideaService; 
		this.userService = userService;
	} 

	@RequestMapping("/ideas") 
	public String index(Model model, HttpSession session) { 
		System.out.println("===============================================index()");
		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 System.out.println(loggedInUserId);
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }		

		User loggedInUser = userService.findUserById(loggedInUserId);
        model.addAttribute("user", loggedInUser);		
		 
		List<Idea> ideas = ideaService.allIdeas();
		model.addAttribute("ideas", ideas);
		
		return "/ideas/dashboardPage.jsp"; 
	} 
	
	@RequestMapping("/ideas/lowlikes") 
	public String indexlow(Model model, HttpSession session) { 
		System.out.println("===============================================indexlow()");
		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 System.out.println(loggedInUserId);
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }		

		User loggedInUser = userService.findUserById(loggedInUserId);
        model.addAttribute("user", loggedInUser);		
		 
		List<Idea> ideas = ideaService.allIdeasLikersAsc();
		model.addAttribute("ideas", ideas);
		
		return "/ideas/dashboardPage.jsp"; 
	} 	

	@RequestMapping("/ideas/highlikes") 
	public String indexhigh(Model model, HttpSession session) { 
		System.out.println("===============================================indexhigh()");
		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 System.out.println(loggedInUserId);
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }		

		User loggedInUser = userService.findUserById(loggedInUserId);
        model.addAttribute("user", loggedInUser);		
		 
		List<Idea> ideas = ideaService.allIdeasLikersDesc();
		model.addAttribute("ideas", ideas);
		
		return "/ideas/dashboardPage.jsp"; 
	} 		
	
	@RequestMapping("/ideas/new") 
	public String newBook(@ModelAttribute("idea") Idea idea, Model model, HttpSession session) {
		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }	

		User loggedInUser = userService.findUserById(loggedInUserId);
        model.addAttribute("user", loggedInUser);			 
		 
		return "/ideas/newidea.jsp"; 
	} 
	
	 @RequestMapping("/ideas/{ideaid}")
	 public String likeIdea(@PathVariable("ideaid") Long ideaid, HttpSession session, Model model ) {

		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 if ((loggedInUserId.equals(null))) {
			 return "redirect:/";
		 }

		 Idea idea = ideaService.findIdea(ideaid);		 
		 User user = userService.findUserById(loggedInUserId);

		 model.addAttribute("idea", idea);
		 model.addAttribute("user", user);
		 
		 return "/ideas/showidea.jsp";
	 }	

	@RequestMapping(value="/ideas/process", method=RequestMethod.POST) 
	public String create(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, @RequestParam("creatorid") Long creatorid) { 
		System.out.println("===============================================create()");
		System.out.println(result.hasErrors());
		if (result.hasErrors()) { 
			System.out.println("ERROR ===============================================create()");
			return "ideas/newidea.jsp"; 
		} else {
			User creator = userService.findUserById(creatorid);
			System.out.println(creator);
			idea.setCreator(creator);
			ideaService.createIdea(idea); 
			return "redirect:/ideas"; 
		} 
	} 

	@RequestMapping(value="/ideas/update", method=RequestMethod.POST) 
	 public String update(@RequestParam("content") String content,
				@RequestParam("creatorid") Long creatorid,
				@RequestParam("ideaid") Long ideaid,
				@ModelAttribute("idea") Idea idea, Model model, BindingResult result) {
		
		System.out.println("===============================================update()");
		System.out.println(content);
		System.out.println(creatorid);
		System.out.println(ideaid);
		
		idea = ideaService.findIdea(ideaid);
		User creator = userService.findUserById(creatorid);
		
		if(result.hasErrors() || content == "") {
			System.out.println("ERRORS ===============================================update()");
			model.addAttribute("idea", idea);
			model.addAttribute("user", creator);
			model.addAttribute("error", "Content field cannot be blank!");
			return "/ideas/editidea.jsp";
		}
		
		System.out.println("NO ERRORS ===============================================update()");
		idea.setContent(content);
		idea.setCreator(creator);
		ideaService.updateIdea(idea);
		return "redirect:/ideas";
	}
	
	
	 @RequestMapping("/ideas/{ideaid}/like/{userid}")
	 public String likeIdea(@PathVariable("ideaid") Long ideaid, @PathVariable("userid") Long userid, HttpSession session, Model model ) {

		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 if ((loggedInUserId.equals(null))) {
			 return "redirect:/";
		 }

		 Idea idea = ideaService.findIdea(ideaid);		 
		 User user = userService.findUserById(loggedInUserId);
		 List<User> likers = idea.getLikers();
		 likers.add(user);
		 idea.setLikers(likers);
		 ideaService.updateIdea(idea);
		 
		 return "redirect:/ideas";
	 }	
	
	 @RequestMapping("/ideas/{ideaid}/unlike/{userid}")
	 public String unlikeIdea(@PathVariable("ideaid") Long ideaid, @PathVariable("userid") Long userid, HttpSession session, Model model ) {

		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 if ((loggedInUserId.equals(null))) {
			 return "redirect:/";
		 }

		 Idea idea = ideaService.findIdea(ideaid);		 
		 User user = userService.findUserById(loggedInUserId);
		 List<User> likers = idea.getLikers();
		 likers.remove(user);
		 idea.setLikers(likers);
		 ideaService.updateIdea(idea);
		 
		 return "redirect:/ideas";
	 }		
	
	 @RequestMapping("/ideas/{ideaid}/edit")
	 public String editEvent(@PathVariable("ideaid") Long ideaid, HttpSession session, Model model ) {

		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }		 
		 
		 Idea idea = ideaService.findIdea(ideaid);
		 User user = userService.findUserById(loggedInUserId);
		 		 		 
		 if (!(loggedInUserId.equals((Long) idea.getCreator().getId()))) {
			 return "redirect:/ideas";
		 }
		 
		 model.addAttribute("idea", idea);
		 model.addAttribute("user", user);
		 
		 return "/ideas/editidea.jsp";
	 }	

	 @RequestMapping("/ideas/{ideaid}/delete")
	 public String deleteEvent(@PathVariable("ideaid") Long ideaid, HttpSession session, Model model ) {

		 Long loggedInUserId = (Long) session.getAttribute("userId");
		 if (loggedInUserId == null) {
			 return "redirect:/";
		 }		 
		 
		 Idea idea = ideaService.findIdea(ideaid);
		 		 		 
		 if (!(loggedInUserId.equals((Long) idea.getCreator().getId()))) {
			 return "redirect:/ideas";
		 }
		 
		 ideaService.deleteIdea(idea);
		 
		 return "redirect:/ideas";
	 }	
	 
	
}
