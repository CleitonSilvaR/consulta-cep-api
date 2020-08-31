package com.cleitons.silvar.consultaCepApi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cleitons.silvar.consultaCepApi.model.dto.UsuarioDTO;
import com.cleitons.silvar.consultaCepApi.model.entity.Usuario;
import com.cleitons.silvar.consultaCepApi.service.impl.UsuarioServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Slf4j
@Api("Api Usuarios")
public class UsuarioController {
	
	private final UsuarioServiceImpl usuarioService;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cadastro de usuario com permissão para acessar api consulta cep")
	@ApiResponses({
		@ApiResponse(code = 201, response = UsuarioDTO.class, message = "Usuário cadastrado com sucesso!")
	})
	public UsuarioDTO salvar(@RequestBody @Valid Usuario usuario ) {
		log.info("Salvando usuario login {} {}", usuario.getLogin(), usuario.getSenha());
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		return new UsuarioDTO(usuarioService.salvar(usuario));
	}
}
