package it.epicode.azienda_energia.datalayer.repositories;

import java.util.Optional;

import it.epicode.azienda_energia.datalayer.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Roles, Long>{
	
	Optional<Roles> findOneByName(String roleName);

}
