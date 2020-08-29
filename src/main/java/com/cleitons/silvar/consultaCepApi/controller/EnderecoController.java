package com.cleitons.silvar.consultaCepApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleitons.silvar.consultaCepApi.model.Endereco;
import com.cleitons.silvar.consultaCepApi.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
	
	private final EnderecoService enderecoService;
	
	@GetMapping(value = "/{cep}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity obterPorCep(@PathVariable String cep) {
		System.out.println("====> " + cep);
		
		Endereco endereco = enderecoService.obterPorCep(cep);
		return new ResponseEntity(endereco, HttpStatus.OK);
	}
}
