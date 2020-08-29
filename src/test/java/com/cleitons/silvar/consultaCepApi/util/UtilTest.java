package com.cleitons.silvar.consultaCepApi.util;

import com.cleitons.silvar.consultaCepApi.model.entity.Endereco;

public final class UtilTest {

	public static Endereco gerarEndereco() {

		return Endereco.builder()
				.cep("80210320")
				.bairro("Jardim Botânico")
				.cidade("Curitiba").estado("PR")
				.rua("Rua Bento Azambuja").build();
	}
}
