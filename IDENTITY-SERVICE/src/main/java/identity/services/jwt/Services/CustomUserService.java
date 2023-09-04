package identity.services.jwt.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import identity.services.jwt.Config.CustomUserDetails;
import identity.services.jwt.Entity.User;
import identity.services.jwt.Repositories.UserRepo;

public class CustomUserService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> myUser = userRepo.findByName(username);
		return myUser.map(CustomUserDetails::new).orElseThrow(() -> new RuntimeException("User not found with name :"+ username));
	}

}
