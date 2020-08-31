package com.cleitons.silvar.consultaCepApi.model.dto;

import com.cleitons.silvar.consultaCepApi.model.entity.Usuario;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Usuario", description = "Objeto com login do usuário cadastrado")
public class UsuarioDTO {
	
	private String login;

	public UsuarioDTO(Usuario usuario) {
		this.login = usuario.getLogin();
	}
}
