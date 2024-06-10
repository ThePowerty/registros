package com.Nerea.regis.repository;

import com.Nerea.regis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmsRepository extends JpaRepository<User, Long> {
}
