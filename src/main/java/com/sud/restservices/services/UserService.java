package com.sud.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sud.restservices.entities.User;
import com.sud.restservices.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
		
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
		
	}
	
	public Optional<User> getUserbyId(long id) {
		Optional<User>user = userRepository.findById(id);
		
		return user;
		
	}
	public User updateUserbyId(long id,User user) {
		user.setId(id);
		 return userRepository.save(user);
		
	}
	
	public void deleteUserbyId(long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
		
	}
	public User getUserbyUserName(String userName) {
		return userRepository.findByUserName(userName);
		
	}
	
}
