package it.epicode.azienda_energia.datalayer.repositories;

import java.util.List;
import java.util.Optional;

import it.epicode.azienda_energia.datalayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findOneByUsernameAndPassword(String username, String password);
	Optional<User> findOneByUsername(String username);
	List<User> findByRoles_RoleType(String role);
}
