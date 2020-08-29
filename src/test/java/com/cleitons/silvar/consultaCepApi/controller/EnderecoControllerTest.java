package com.cleitons.silvar.consultaCepApi.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cleitons.silvar.consultaCepApi.model.entity.Endereco;
import com.cleitons.silvar.consultaCepApi.service.EnderecoService;
import com.cleitons.silvar.consultaCepApi.util.UtilTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = EnderecoController.class)
@AutoConfigureMockMvc
public class EnderecoControllerTest {
	
	static String ENDERECO_API = "/endereco";
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	EnderecoService enderecoService;
	
	@Test
	@DisplayName("Deve Consultar Um Endereco Via Uma Requisicao GET")
	public void obterEnderecoViaGet() throws Exception {
		Endereco enderecoGerado = UtilTest.gerarEndereco();
		
		Mockito.when( enderecoService.consultarEndereco(enderecoGerado.getCep()) ).thenReturn(enderecoGerado);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(new StringBuilder(ENDERECO_API).append("/").append(enderecoGerado.getCep()).toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);;
		
		mvc.perform(request)
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath("cep").value(enderecoGerado.getCep()) )
				.andExpect( MockMvcResultMatchers.jsonPath("rua").value(enderecoGerado.getRua()) )
				.andExpect( MockMvcResultMatchers.jsonPath("cidade").value(enderecoGerado.getCidade()) )
				.andExpect( MockMvcResultMatchers.jsonPath("bairro").value(enderecoGerado.getBairro()) )
				.andExpect( MockMvcResultMatchers.jsonPath("estado").value(enderecoGerado.getEstado()) );
	}
	
	@Test
	@DisplayName("Nao Deve Consultar Um Endereco Via Uma Requisicao GET")
	public void nbterEnderecoViaGet() throws Exception {
		Endereco endereco = new Endereco("CEP inválido");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(new StringBuilder(ENDERECO_API).append("/11111111").toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		mvc.perform(request)
				.andExpect( MockMvcResultMatchers.status().isBadRequest() )
				.andExpect( MockMvcResultMatchers.jsonPath("erro").value(endereco.getErro()) );
	}
	
	@Test
	@DisplayName("Deve Consultar Um Endereco Via Uma Requisicao POST")
	public void obterEnderecoViaPost() throws Exception {
		
		Endereco enderecoGerado = UtilTest.gerarEndereco();
		
		Mockito.when( enderecoService.consultarEndereco(Mockito.anyString()) ).thenReturn(enderecoGerado);
		
		String json = new ObjectMapper().writeValueAsString(new Endereco(1L, enderecoGerado.getCep()));
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(ENDERECO_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mvc.perform(request)
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( MockMvcResultMatchers.jsonPath("cep").value(enderecoGerado.getCep()) )
				.andExpect( MockMvcResultMatchers.jsonPath("rua").value(enderecoGerado.getRua()) )
				.andExpect( MockMvcResultMatchers.jsonPath("cidade").value(enderecoGerado.getCidade()) )
				.andExpect( MockMvcResultMatchers.jsonPath("bairro").value(enderecoGerado.getBairro()) )
				.andExpect( MockMvcResultMatchers.jsonPath("estado").value(enderecoGerado.getEstado()) );
		
	}
	
	@Test
	@DisplayName("Nao Deve Consultar Um Endereco Via Uma Requisicao POST")
	public void naoObterEnderecoViaPost() throws Exception {
		
		Endereco endereco = new Endereco("CEP inválido");
		
		String json = new ObjectMapper().writeValueAsString(new Endereco(1L, endereco.getCep()));
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(ENDERECO_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mvc.perform(request)
				.andExpect( MockMvcResultMatchers.status().isBadRequest() )
				.andExpect( MockMvcResultMatchers.jsonPath("erro").value(endereco.getErro()) );
		
	}
}
