package com.cleitons.silvar.consultaCepApi.service;

import com.cleitons.silvar.consultaCepApi.model.entity.Endereco;

public interface EnderecoService {
	
	Endereco consultarEndereco(String cep);
}
