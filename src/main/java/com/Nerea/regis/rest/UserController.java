package com.Nerea.regis.rest;

import com.Nerea.regis.entities.Films;
import com.Nerea.regis.entities.User;
import com.Nerea.regis.security.dto.MessageResponse;
import com.Nerea.regis.security.dto.UserUpdateRequest;
import com.Nerea.regis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<User> delete(@PathVariable String email){
        log.info("REST request to delete an user by email");

        this.userService.deleteByEmail(email);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{email}")
    public ResponseEntity<MessageResponse> update(@PathVariable String email, @RequestBody UserUpdateRequest request){
        log.info("REST request to update user by email");
        MessageResponse messageResponse = userService.updateUser(email, request).getBody();
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/{email}/pendientes")
    public ResponseEntity<List<Films>> getPendientes(@PathVariable String email) {
        log.info("REST request to get list of film named Pendientes");
        List<Films> pendientes = userService.getPendientes(email);
        return ResponseEntity.ok(pendientes);
    }

    @GetMapping("/{email}/favoritas")
    public ResponseEntity<List<Films>> getFavoritas(@PathVariable String email) {
        log.info("REST request to get list of film named Favoritas");
        List<Films> favoritas = userService.getFavoritas(email);
        return ResponseEntity.ok(favoritas);
    }

    @GetMapping("/{email}/vistas")
    public ResponseEntity<List<Films>> getVistas(@PathVariable String email) {
        log.info("REST request to get list of film named Vistas");
        List<Films> vistas = userService.getVistas(email);
        return ResponseEntity.ok(vistas);
    }

    @PostMapping("/{email}/pendientes")
    public ResponseEntity<MessageResponse> updatePendientes(@PathVariable String email, @RequestBody List<Films> pendientes) {
        log.info("REST request to update list of film named Pendientes");
        MessageResponse messageResponse = userService.updateFavoritas(email, pendientes).getBody();
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/{email}/favoritas")
    public ResponseEntity<MessageResponse> updateFavoritas(@PathVariable String email, @RequestBody List<Films> favoritas) {
        log.info("REST request to update list of film named Favoritas");
        MessageResponse messageResponse = userService.updateFavoritas(email, favoritas).getBody();
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/{email}/vistas")
    public ResponseEntity<MessageResponse> updateVistas(@PathVariable String email, @RequestBody List<Films> vistas) {
        log.info("REST request to update list of film named Vistas");
        MessageResponse messageResponse = userService.updateFavoritas(email, vistas).getBody();
        return ResponseEntity.ok(messageResponse);
    }

}
