package com.freddo.beltrevproject.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.freddo.beltrevproject.models.User;
import com.freddo.beltrevproject.repos.UserRepo;

@Service 
public class UserService { 
	// adding the user repo as a dependency 
	private final UserRepo userRepo; 

	public UserService(UserRepo userRepo) { 
		this.userRepo = userRepo; 
	}
	
	// returns all the users 
	public List<User> allUsers() { 
	    return userRepo.findAll(); 
	} 
	
	// creates a user 
	public User createUser(User user) { 
	    return userRepo.save(user); 
	} 
	
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }	
	
	// retrieves a user 
	public User findUserById(Long id) { 
	    Optional<User> optionalUser = userRepo.findById(id); 
	    if(optionalUser.isPresent()) { 
	        return optionalUser.get(); 
	    } else { 
	        return null; 
	    } 
	} 
}
