package com.Nerea.regis.service;

import com.Nerea.regis.entities.Films;
import com.Nerea.regis.entities.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    // spring repository methods

    User save(User user);

    void deleteAll();

    // custom methods

    void deleteByEmail(String email);

    List<Films> getPendientes(String email);
    List<Films> getFavoritas(String email);
    List<Films> getVistas(String email);
    void updatePendientes(String email, List<Films> favoritas);
    void updateFavoritas(String email, List<Films> favoritas);
    void updateVistas(String email, List<Films> favoritas);

}
