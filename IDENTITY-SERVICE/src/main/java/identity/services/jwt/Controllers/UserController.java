package identity.services.jwt.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import identity.services.jwt.DTO.UserDto;
import identity.services.jwt.Entity.User;
import identity.services.jwt.Services.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	
	
	@PostMapping("/requestToken")
	public String requestToken(@RequestBody UserDto user) {
		 Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		 if(auth.isAuthenticated()) {
			 return userService.generateToken(user.getName());
		 }else {
			throw new RuntimeException("Invalid Access");
		}
		 
		
	}
	
	
	@GetMapping("/validateToken")
	public String validateToken(@RequestParam("token") String token) {
		userService.validateToken(token);
		return "Token is Valid";
	}

}
