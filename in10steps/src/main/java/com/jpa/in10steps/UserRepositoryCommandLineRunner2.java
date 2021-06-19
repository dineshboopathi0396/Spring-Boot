package com.jpa.in10steps;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jpa.in10steps.entity.User;
import com.jpa.in10steps.service.UserDAOService;
import com.jpa.in10steps.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner2 implements CommandLineRunner{

	private static final Logger log = 
			LoggerFactory.getLogger(UserRepositoryCommandLineRunner2.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User("Manoj", "Admin");
		userRepository.save(user);
		log.info("New User is Created : " + user.getId());
		Optional<User> userwithOne = userRepository.findById(1l);
		log.info("userwithOne : " + userwithOne);
		
		List<User> AllUsers = userRepository.findAll();
		log.info("AllUsers : " + AllUsers);
	}

}
