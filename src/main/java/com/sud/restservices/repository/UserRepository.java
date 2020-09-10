package com.sud.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sud.restservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//custom method implemented easily
	User findByUserName(String userName);

}
