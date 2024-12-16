package com.hexaware.amazecare1.restcontroller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare1.dto.AuthRequest;
import com.hexaware.amazecare1.dto.ForgotPassword;
import com.hexaware.amazecare1.entities.UserInfo;
import com.hexaware.amazecare1.service.JwtService;
import com.hexaware.amazecare1.service.UserService;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	UserService service;

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@PostMapping("/registration/new")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}

	@PostMapping("/login/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		String token = null;

		if (authentication.isAuthenticated()) {

			// call generate token method from jwtService class

			token = jwtService.generateToken(authRequest.getUsername());

			logger.info("Token : " + token);

		} else {

			logger.info("invalid");

			throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");

		}

		return token;

	}
	 @GetMapping("/role")
	    public String getUserRole(@RequestParam String username) {
		 System.out.println("Received username: " + username); // Debugging
	        return service.getUserRole(username);
	    }
	 @GetMapping("/check-username")
	    public ResponseEntity<Optional<UserInfo>> checkUsername(@RequestParam String username) {
	        Optional<UserInfo> exists = service.checkUsernameExists(username);
	        return ResponseEntity.ok(exists);
	    }
	 
	 @PostMapping("/forgot-password")
	    public ResponseEntity<String> resetPassword(@RequestBody ForgotPassword request) {
	        boolean isUpdated = service.updatePassword(request.getUsername(), request.getPassword());
	        if (isUpdated) {
	            return ResponseEntity.ok("Password updated successfully");
	        }
			return null; 
	    }

}
