package com.cleitons.silvar.consultaCepApi.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cleitons.silvar.consultaCepApi.model.entity.Endereco;
import com.cleitons.silvar.consultaCepApi.util.UtilTest;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EnderecoRepositoryTest {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	@DisplayName("Deve Verificar A Existencia De Um Endereco")
	public void verificarAExistenciaDeUmEndereco() {
		Endereco endereco = UtilTest.gerarEndereco();
		entityManager.persist(endereco);
		
		Optional<Endereco> enderecoConsulta = enderecoRepository.findByCep(endereco.getCep());
		
		Assertions.assertThat(enderecoConsulta.isPresent()).isTrue();
	}
	
	@Test
	@DisplayName("Deve Retornar Falso Quando Nao Houver Endereco Cadastrado Com O Cep")
	public void deveRetornarFalsoQuandoNaoHouverEnderecoCadastradoComOCep() {
		String cep = "11111111";
		
		Optional<Endereco> enderecoConsulta = enderecoRepository.findByCep(cep);
		
		Assertions.assertThat(enderecoConsulta.isPresent()).isFalse();
	}
}
