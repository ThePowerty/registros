package com.Nerea.regis.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @NaturalId
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ElementCollection
    @CollectionTable(name = "pendientes", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "email"))
    private List<Films> pendientes = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "favoritas", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "email"))
    private List<Films> favoritas = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "vistas", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "email"))
    private List<Films> vistas = new ArrayList<>();


    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Films> getPendientes() {
        return pendientes;
    }

    public void setPendientes(List<Films> pendientes) {
        this.pendientes = pendientes;
    }

    public List<Films> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(List<Films> favoritas) {
        this.favoritas = favoritas;
    }

    public List<Films> getVistas() {
        return vistas;
    }

    public void setVistas(List<Films> vistas) {
        this.vistas = vistas;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
