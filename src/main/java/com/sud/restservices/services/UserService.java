package com.sud.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sud.restservices.entities.User;
import com.sud.restservices.exceptions.UserExistsException;
import com.sud.restservices.exceptions.UserNotFoundException;
import com.sud.restservices.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {

		return userRepository.findAll();

	}

	public User createUser(User user) throws UserExistsException {
		Optional<User> optionalUser = userRepository.findById(user.getId());

		if (optionalUser.isPresent()) {
			throw new UserExistsException("Cannot Create : user already Exists in user Repository");
		}
		return userRepository.save(user);

	}

	public Optional<User> getUserbyId(long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Cannot Find : user not found in user Repository");
		}
		return user;

	}

	public User updateUserbyId(long id, User user) throws UserNotFoundException {

		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("Cannot Update : user not found in user Repository");
		}
		user.setId(id);
		return userRepository.save(user);

	}

	public void deleteUserbyId(long id) throws UserNotFoundException {

		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("Cannot Delete : user not found in user Repository");
		}
		userRepository.deleteById(id);

	}

	public User getUserbyUserName(String userName) {
		return userRepository.findByUserName(userName);

	}

}
