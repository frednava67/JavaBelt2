package com.freddo.beltrevproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.freddo.beltrevproject.models.User;
import com.freddo.beltrevproject.services.UserService;
import com.freddo.beltrevproject.validator.UserValidator;

@Controller
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    
    // NEW
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

	@RequestMapping("/")
	public String loginReg(@ModelAttribute("user") User user, @ModelAttribute("error") String error, Model model) {
        System.out.println("===============================================loginReg()");
        model.addAttribute("error", error);
		return "/loginreg/loginregPage.jsp";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttrs) {
		if (userService.authenticateUser(email, password)) {
			User user = userService.findByEmail(email);
			session.setAttribute("userid", user.getId());
			return "redirect:/events";
		} else {
			redirectAttrs.addFlashAttribute("error", "User was not successfully authenticated");		 
			return "redirect:/";
		}

		//return "loginreg/loginregPage.jsp";
	}
	        
	@RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        System.out.println("===============================================registerUser()");
		userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "/loginreg/loginregPage.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("userId", u.getId());
        return "redirect:/events";
    }
    
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
        System.out.println("===============================================logout()");
		session.invalidate();
        return "redirect:/";
	}
}

