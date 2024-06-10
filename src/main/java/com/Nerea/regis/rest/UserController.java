package com.Nerea.regis.rest;

import com.Nerea.regis.entities.Films;
import com.Nerea.regis.entities.User;
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

    @GetMapping("/{email}/pendientes")
    public ResponseEntity<List<Films>> getPendientes(@PathVariable String email) {
        List<Films> pendientes = userService.getPendientes(email);
        return ResponseEntity.ok(pendientes);
    }

    @GetMapping("/{email}/favoritas")
    public ResponseEntity<List<Films>> getFavoritas(@PathVariable String email) {
        List<Films> favoritas = userService.getFavoritas(email);
        return ResponseEntity.ok(favoritas);
    }

    @GetMapping("/{email}/vistas")
    public ResponseEntity<List<Films>> getVistas(@PathVariable String email) {
        List<Films> vistas = userService.getVistas(email);
        return ResponseEntity.ok(vistas);
    }

    @PostMapping("/{email}/pendientes")
    public ResponseEntity<Void> updatePendientes(@PathVariable String email, @RequestBody List<Films> pendientes) {
        userService.updateFavoritas(email, pendientes);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{email}/favoritas")
    public ResponseEntity<Void> updateFavoritas(@PathVariable String email, @RequestBody List<Films> favoritas) {
        userService.updateFavoritas(email, favoritas);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{email}/vistas")
    public ResponseEntity<Void> updateVistas(@PathVariable String email, @RequestBody List<Films> vistas) {
        userService.updateFavoritas(email, vistas);
        return ResponseEntity.ok().build();
    }

}
