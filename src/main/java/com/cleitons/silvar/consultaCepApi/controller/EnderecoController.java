package com.cleitons.silvar.consultaCepApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleitons.silvar.consultaCepApi.model.dto.EnderecoDTO;
import com.cleitons.silvar.consultaCepApi.model.entity.Endereco;
import com.cleitons.silvar.consultaCepApi.service.EnderecoService;
import com.cleitons.silvar.consultaCepApi.util.Util;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
	
	private final EnderecoService enderecoService;
	
	@GetMapping(value = "/{cep}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity obterEndereco(@PathVariable String cep) {
		
		if (!Util.isValorValido(cep)) {
			return new ResponseEntity(new Endereco("CEP inválido"), HttpStatus.BAD_REQUEST);
		}
		
		return consultarEnderecoPorCep(cep);
	}
	
	@PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity obterEndereco(@RequestBody EnderecoDTO enderecoDTO ) {
		
		if (enderecoDTO == null || !Util.isValorValido(enderecoDTO.getCep())) {
			return new ResponseEntity(new Endereco("CEP inválido"), HttpStatus.BAD_REQUEST);
		}
		return consultarEnderecoPorCep(enderecoDTO.getCep());
	}
	
	private ResponseEntity consultarEnderecoPorCep(String cep) {
		Endereco endereco = enderecoService.consultarEndereco(cep);
		
		if (endereco != null) {
			return new ResponseEntity(endereco, HttpStatus.OK);
		} else {
			return new ResponseEntity(new Endereco("CEP inválido"), HttpStatus.BAD_REQUEST);
		}
	}
}
