package com.Nerea.regis.security.service;

import com.Nerea.regis.entities.User;
import com.Nerea.regis.exception.RegisterException;
import com.Nerea.regis.repository.UserRepository;
import com.Nerea.regis.security.dto.*;
import com.Nerea.regis.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    JwtService jwtService;

    public AuthenticationResponse login(LoginRequest authRequest) {

        // Autentificacion del usuario dentro de la base de datos
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword()
        );

        authenticationManager.authenticate(authToken);

        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt, user);

    }

    public ResponseEntity<MessageResponse> register(RegisterRequest authRequest) {
        // Check 1: username
        if (userRepository.existsByUsername(authRequest.getUsername())) {
            throw new RegisterException("Error: Username is already taken!");
        }

        // Check 2: email
        if (userRepository.existsByEmail(authRequest.getEmail())) {
            throw new RegisterException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(
                authRequest.getUsername(),
                authRequest.getEmail(),
                encoder.encode(authRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());

        return extraClaims;
    }
}
