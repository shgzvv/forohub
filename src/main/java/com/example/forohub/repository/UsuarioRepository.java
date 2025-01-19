package com.example.forohub.repository;

import com.example.forohub.models.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    UserDetails findByLogin(String username);
}