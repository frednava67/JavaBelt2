package com.freddo.beltrevproject.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.freddo.beltrevproject.models.User;

@Repository 

public interface UserRepo extends CrudRepository<User, Long>{ 
	List<User> findAll(); 
	User findByEmail(String email);
}
