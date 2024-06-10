package com.Nerea.regis.security.rest;

import com.Nerea.regis.security.dto.*;
import com.Nerea.regis.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest authRequest){

        AuthenticationResponse jwtDTO = authenticationService.login(authRequest);

        return ResponseEntity.ok(jwtDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(
            @RequestBody RegisterRequest authRequest) {

        MessageResponse messageResponse = authenticationService.register(authRequest).getBody();

        return ResponseEntity.ok(messageResponse);
    }
}
