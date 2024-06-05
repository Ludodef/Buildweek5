package it.epicode.azienda_energia.config;

import it.epicode.azienda_energia.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.Mapper;
import it.epicode.azienda_energia.datalayer.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Configuration
public class BeansConfiguration {

	@Bean
	public Pageable defaultPageable() {
		int page = 0; // Numero di pagina predefinito (prima pagina)
		int size = 10; // Dimensione della pagina predefinita
		return PageRequest.of(page, size);
	}
	@Bean
	@Scope("singleton")
	Mapper<RegisterUserDTO, User> mapRegisterUser2UserEntity() {
		return (input) -> User.builder()
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withPassword(input.getPassword())
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<User, RegisteredUserDTO> mapUserEntity2RegisteredUser() {
		return (input) -> RegisteredUserDTO.builder()
				.withId(input.getId())
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withRoles(input.getRoles())
				.build();
	}
	
	@Bean
	@Scope("singleton")
	Mapper<User, LoginResponseDTO> mapUserEntity2LoginResponse() {
		return (input) -> LoginResponseDTO.builder()
				.withId(input.getId())
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withUsername(input.getUsername())
				.withRoles(input.getRoles())
				.build();
	}
}
