package identity.services.jwt.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import identity.services.jwt.Entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByName(String username);
	
}
