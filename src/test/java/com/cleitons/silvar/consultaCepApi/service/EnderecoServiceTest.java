package com.cleitons.silvar.consultaCepApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
public class EnderecoServiceTest {
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	@DisplayName("Deve Consultar Endereco Sem Erros")
	public void consultarEnderecoSemErros() {
		Endereco endereco = UtilTest.gerarEndereco();
		entityManager.persist(endereco);
		
		assertDoesNotThrow(()-> {
			Endereco enderecoConsulta = enderecoService.consultarEndereco(endereco.getCep());
			
			assertThat(enderecoConsulta).isNotNull();
			assertThat(enderecoConsulta.getCep()).isEqualTo(endereco.getCep());
			assertThat(enderecoConsulta.getBairro()).isEqualTo(endereco.getBairro());
			assertThat(enderecoConsulta.getRua()).isEqualTo(endereco.getRua());
			assertThat(enderecoConsulta.getCidade()).isEqualTo(endereco.getCidade());
			assertThat(enderecoConsulta.getEstado()).isEqualTo(endereco.getEstado());
		});
	}
	
	@Test
	@DisplayName("Nao Deve Consultar Endereco")
	public void consultarEnderecoInvalido() {
		Endereco endereco = UtilTest.gerarEndereco();
		
		assertDoesNotThrow(()-> {
			Endereco enderecoConsulta = enderecoService.consultarEndereco(endereco.getCep());
			assertThat(enderecoConsulta).isNull();
		});
	}

}
