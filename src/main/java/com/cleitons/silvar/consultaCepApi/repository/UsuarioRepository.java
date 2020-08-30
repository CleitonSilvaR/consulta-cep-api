package com.cleitons.silvar.consultaCepApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleitons.silvar.consultaCepApi.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByLogin(String login);

}
