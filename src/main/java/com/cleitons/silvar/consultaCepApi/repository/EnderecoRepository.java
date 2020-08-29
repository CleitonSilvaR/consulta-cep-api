package com.cleitons.silvar.consultaCepApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleitons.silvar.consultaCepApi.model.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Optional<Endereco> findByCep(String cep);

}
