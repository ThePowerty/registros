package com.Nerea.regis.service;

import com.Nerea.regis.entities.Films;
import com.Nerea.regis.entities.User;
import com.Nerea.regis.repository.UserRepository;
import com.Nerea.regis.security.dto.MessageResponse;
import com.Nerea.regis.security.dto.UserUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder encoder;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User save(User user) {
        log.info("Creating / Updating User");

        if (!validate(user))
            return null;

        return this.userRepository.save(user);
    }

    private boolean validate(User user) {
        if (user == null) {
            log.warn("Trying to create null user");
            return false;
        }
        return true;
    }

    @Override
    public void deleteAll() {
        log.info("Deleting all users");
        this.userRepository.deleteAll();
    }

    @Override
    public void deleteByEmail(String email) {
        log.info("Deleting user");
        this.userRepository.deleteByEmail(email);
    }

    @Override
    public ResponseEntity<MessageResponse> updateUser(String email, UserUpdateRequest request) {
        log.info("Updated user name and password");
        this.userRepository.findByEmail(email).ifPresent(user -> {
            user.setUsername(request.getUsername());
            user.setPassword(encoder.encode(request.getPassword()));
            this.userRepository.save(user);
        });
        return ResponseEntity.ok(new MessageResponse("User updated!"));
    }

    @Override
    public List<Films> getPendientes(String email) {
        log.info("Get list of Pendientes from user");
        return this.userRepository.findPendientesBy(email);
    }

    @Override
    public List<Films> getFavoritas(String email) {
        log.info("Get list of Favoritas from user");
        return this.userRepository.findFavoritasBy(email);
    }

    @Override
    public List<Films> getVistas(String email) {
        log.info("Get list of Vistas from user");
        return this.userRepository.findVistasBy(email);
    }

    @Override
    public ResponseEntity<MessageResponse> updatePendientes(String email, List<Films> pendientes) {
        this.userRepository.findByEmail(email).ifPresent(user -> {
            user.setPendientes(pendientes);
            this.userRepository.save(user);
        });
        return ResponseEntity.ok(new MessageResponse("List Pendientes updated!"));
    }

    @Override
    public ResponseEntity<MessageResponse> updateFavoritas(String email, List<Films> favoritas) {
        this.userRepository.findByEmail(email).ifPresent(user -> {
            user.setFavoritas(favoritas);
            this.userRepository.save(user);
        });
        return ResponseEntity.ok(new MessageResponse("List Favoritas updated!"));
    }

    @Override
    public ResponseEntity<MessageResponse> updateVistas(String email, List<Films> vistas) {
        this.userRepository.findByEmail(email).ifPresent(user -> {
            user.setVistas(vistas);
            this.userRepository.save(user);
        });
        return ResponseEntity.ok(new MessageResponse("List Vistas updated!"));
    }

}
