package it.epicode.azienda_energia.businesslayer.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.azienda_energia.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.azienda_energia.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.azienda_energia.businesslayer.services.interfaces.MailService;
import it.epicode.azienda_energia.businesslayer.services.interfaces.Mapper;
import it.epicode.azienda_energia.businesslayer.services.interfaces.UserService;
import it.epicode.azienda_energia.config.JwtUtils;
import it.epicode.azienda_energia.datalayer.entities.Roles;
import it.epicode.azienda_energia.datalayer.entities.User;
import it.epicode.azienda_energia.datalayer.repositories.RolesRepository;
import it.epicode.azienda_energia.datalayer.repositories.UsersRepository;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.InvalidLoginException;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.duplicated.DuplicateEmailException;
import it.epicode.azienda_energia.presentationlayer.api.exceptions.duplicated.DuplicateUsernameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private RolesRepository roles;

    @Autowired
    private Pageable defaultPageable;

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private JwtUtils jwt;

    @Autowired
    Mapper<RegisterUserDTO, User> mapEntity;

    @Autowired
    Mapper<User, RegisteredUserDTO> mapRegisteredUser;

    @Autowired
    Mapper<User, LoginResponseDTO> mapLogin;


    @Value("${CLOUDINARY_URL}")
    private String cloudinaryUrl;

    @Autowired
    MailService mailService;

    @Override
    public RegisteredUserDTO register(RegisterUserDTO newUser) {
        var emailDuplicated = usersRepository.findByEmail(newUser.getEmail());
        var usernameDuplicated = usersRepository.findOneByUsername(newUser.getUsername());

        if (emailDuplicated.isPresent()) {
            throw new DuplicateEmailException(newUser.getEmail());
        }else if (usernameDuplicated.isPresent()) {
            throw new DuplicateUsernameException(newUser.getUsername());
        } else {
            try {
                var userEntity = mapEntity.map(newUser);
                var p = encoder.encode(newUser.getPassword());
                log.info("Password encrypted: {}", p);
                userEntity.setPassword(p);
                        var totalUsers = this.getAll(defaultPageable);

                        if (totalUsers.getTotalElements() == 0)
                        {
                            userEntity.getRoles().add(
                                    Roles.builder()
                                            .withRoleType("ADMIN")
                                            .build()
                            );
                        } else {
                            userEntity.getRoles().add(
                                    Roles.builder()
                                            .withRoleType("USER")
                                            .build()
                            );
                        }
                var u = mapRegisteredUser.map(usersRepository.save(userEntity));
                       mailService.sendMail(newUser.getEmail(), "Registrazione avvenuta con successo",
                                newUser.getFirstName() + " " + newUser.getLastName() + " hai effettuato con successo la tua registrazione" );
                       return u;
            } catch (Exception e) {
                log.error(String.format("Exception saving user %s", usersRepository), e);
                throw new RuntimeException();
            }
        }
    }

    @Override
    public Optional<LoginResponseDTO> login(String username, String password) {
        try {
            var a = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            a.getAuthorities();
            SecurityContextHolder.getContext().setAuthentication(a);

            var dto = mapLogin.map(usersRepository.findOneByUsername(username).orElseThrow());
            dto.setToken(jwt.generateToken(a));
            return Optional.of(dto);
        } catch (NoSuchElementException e) {
            log.error("User not found", e);
            throw new InvalidLoginException(username, password);
        } catch (AuthenticationException e) {
            log.error("Authentication failed", e);
            throw new InvalidLoginException(username, password);
        }
    }

    @Override
    public RegisteredUserDTO getById(long id) {
        return mapRegisteredUser.map(usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id)));
    }

    @Override
    public Page<User> getAll(Pageable p) {
        return usersRepository.findAll(p);
    }

    @Override
    public RegisteredUserDTO update(long id, String username) {
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));

        var usernameDuplicated = usersRepository.findOneByUsername(username);

        if (usernameDuplicated.isPresent()) {
            throw new DuplicateUsernameException(username);
        } else {
            user.setUsername(username);
            return mapRegisteredUser.map(usersRepository.save(user));
        }
    }


    @Override
    public RegisteredUserDTO delete(Long id) {
        try {
            var u = usersRepository.findById(id).orElseThrow();
            usersRepository.delete(u);
            return mapRegisteredUser.map(u);
        } catch (NoSuchElementException e) {
            log.error(String.format("Cannot find user with id = %s", id), e);
            throw new RuntimeException("Cannot find user...");
        } catch (Exception e) {
            log.error(String.format("Error deleting user with id = %s", id), e);
            throw new RuntimeException();
        }
    }

    @Override
    public RegisteredUserDTO addRole(Long id, String role) {
        var roleEntity = roles.findOneByRoleType(role);
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        if (roleEntity.isEmpty()) {
            throw new RuntimeException("Il ruolo che stai tentando di aggiungere non esiste");
        } else if (user.getRoles().contains(roleEntity.get())) {
            throw new RuntimeException("Il ruolo che stai tentando di aggiungere è gia presente");
        } else {
            user.getRoles().add(roleEntity.get());
            return mapRegisteredUser.map(usersRepository.save(user));
        }
    }

    @Override
    public RegisteredUserDTO removeRole(Long id, String role) {
        var roleEntity = roles.findOneByRoleType(role);
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        //DOVREMMO CONTROLLARE CHE ESISTA ALMENO UN ADMIN E SOPRATTUTO CHE OGNI PEROSNA DEVE AVERE ALMENO UN RUOLO
        if (roleEntity.isEmpty()) {
            throw new RuntimeException("Il ruolo che stai tentando di rimuovere non esiste");
        } else if (!user.getRoles().contains(roleEntity.get())) {
            throw new RuntimeException("Il ruolo che stai tentando di rimuovere non è presente");
        }  else {
            user.getRoles().remove(roleEntity.get());
            return mapRegisteredUser.map(usersRepository.save(user));

        }
    }


    @Override
    public User saveAvatar(long id, MultipartFile file) throws IOException {
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
        var url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        user.setAvatar(url);
        return usersRepository.save(user);
    }



}
