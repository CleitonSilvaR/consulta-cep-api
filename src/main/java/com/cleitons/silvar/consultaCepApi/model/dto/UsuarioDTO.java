package com.cleitons.silvar.consultaCepApi.model.dto;

import com.cleitons.silvar.consultaCepApi.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	private String login;

	public UsuarioDTO(Usuario usuario) {
		this.login = usuario.getLogin();
	}
}
