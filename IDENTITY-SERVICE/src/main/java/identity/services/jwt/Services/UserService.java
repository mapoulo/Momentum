package identity.services.jwt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import identity.services.jwt.Config.AuthConfig;
import identity.services.jwt.Entity.User;
import identity.services.jwt.Repositories.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	
	public void validateToken(String toke) {
		jwtService.validateToken(toke);
	}
	
	

}
