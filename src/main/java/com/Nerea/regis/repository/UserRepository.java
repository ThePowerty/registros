package com.Nerea.regis.repository;

import com.Nerea.regis.entities.Films;
import com.Nerea.regis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("SELECT u.pendientes FROM User u WHERE u.email = :email")
    List<Films> findPendientesBy(@Param("email") String email);
    @Query("SELECT u.favoritas FROM User u WHERE u.email = :email")
    List<Films> findFavoritasBy(@Param("email") String email);
    @Query("SELECT u.vistas FROM User u WHERE u.email = :email")
    List<Films> findVistasBy(@Param("email") String email);

    Boolean deleteByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
