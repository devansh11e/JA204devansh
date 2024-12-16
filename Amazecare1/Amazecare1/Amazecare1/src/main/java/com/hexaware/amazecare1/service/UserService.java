package com.hexaware.amazecare1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare1.entities.UserInfo;
import com.hexaware.amazecare1.repositories.UserInfoRepository;

@Service
public class UserService {
	
	@Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	
	public String addUser(UserInfo userInfo) {  // user registration
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }

	 public String getUserRole(String username) {
	        UserInfo userInfo = repository.findByName(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        return userInfo.getRole(); // Return the role of the user
	    }
	 public Optional<UserInfo> checkUsernameExists(String username) {
	        return repository.findByName(username);
	    }
	 
	 public boolean updatePassword(String username,String password)
	 {Optional<UserInfo> optionalUser = repository.findByName(username);
     
     if (optionalUser.isPresent()) {
         // Extract the UserInfo object
         UserInfo user = optionalUser.get();
         
         // Update the password
         user.setPassword(passwordEncoder.encode(password)); // Encrypt the password
         
         // Save the updated user
         repository.save(user);
         return true;
     } else {
         return false; // User not found
     }
 }
}

