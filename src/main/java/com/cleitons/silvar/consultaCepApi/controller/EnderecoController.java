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
import com.cleitons.silvar.consultaCepApi.model.dto.ErrorDTO;
import com.cleitons.silvar.consultaCepApi.model.entity.Endereco;
import com.cleitons.silvar.consultaCepApi.service.EnderecoService;
import com.cleitons.silvar.consultaCepApi.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
@Slf4j
@Api("Api Consulta cep")
public class EnderecoController {
	
	private final EnderecoService enderecoService;
	
	@GetMapping(value = "/{cep}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("Consulta de endereço via requesição GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = Endereco.class, message = "Cep encontrado com sucesso!"),
		@ApiResponse(code = 400, response = ErrorDTO.class, message = "Cep inválido!"),
		@ApiResponse(code = 500, response = ErrorDTO.class, message = "Erro inesperado")
	})
	public ResponseEntity obterEndereco(
			@PathVariable 
			@ApiParam(name = "cep", value = "Cep para realizar a consulta", required = true)
			String cep) {
		
		if (!Util.isValorValido(cep)) {
			return new ResponseEntity(new ErrorDTO("CEP inválido"), HttpStatus.BAD_REQUEST);
		}
		
		return consultarEnderecoPorCep(cep);
	}
	
	@PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation("Consulta de endereço via requesição POST")
	@ApiResponses({
		@ApiResponse(code = 200, response = Endereco.class, message = "Cep encontrado com sucesso!"),
		@ApiResponse(code = 400, response = ErrorDTO.class, message = "Cep inválido!"),
		@ApiResponse(code = 500, response = ErrorDTO.class, message = "Erro inesperado")
	})
	public ResponseEntity obterEndereco(
			@RequestBody 
			@ApiParam(name = "Endereço", value = "Objeto com valor de cep para consulta", required = true)
			EnderecoDTO enderecoDTO ) {
		
		if (enderecoDTO == null || !Util.isValorValido(enderecoDTO.getCep())) {
			return new ResponseEntity(new ErrorDTO("CEP inválido"), HttpStatus.BAD_REQUEST);
		}
		return consultarEnderecoPorCep(enderecoDTO.getCep());
	}
	
	private ResponseEntity consultarEnderecoPorCep(String cep) {
		try {
			log.info("Consultando cep {}", cep);
			Endereco endereco = enderecoService.consultarEndereco(cep);
			
			if (endereco != null) {
				return new ResponseEntity(endereco, HttpStatus.OK);
			} else {
				return new ResponseEntity(new ErrorDTO("CEP inválido"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ErrorDTO("Oppps, ocorreu um erro inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
