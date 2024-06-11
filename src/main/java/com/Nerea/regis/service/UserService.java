package com.Nerea.regis.service;

import com.Nerea.regis.entities.Films;
import com.Nerea.regis.entities.User;
import com.Nerea.regis.security.dto.MessageResponse;
import com.Nerea.regis.security.dto.UserUpdateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {

    // spring repository methods

    User save(User user);

    void deleteAll();

    // custom methods

    void deleteByEmail(String email);

    ResponseEntity<MessageResponse> updateUser(String email, UserUpdateRequest request);
    List<Films> getPendientes(String email);
    List<Films> getFavoritas(String email);
    List<Films> getVistas(String email);
    ResponseEntity<MessageResponse> updatePendientes(String email, List<Films> favoritas);
    ResponseEntity<MessageResponse> updateFavoritas(String email, List<Films> favoritas);
    ResponseEntity<MessageResponse> updateVistas(String email, List<Films> favoritas);

}
