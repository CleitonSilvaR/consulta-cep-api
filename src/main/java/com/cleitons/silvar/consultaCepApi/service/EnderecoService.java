package com.cleitons.silvar.consultaCepApi.service;

import com.cleitons.silvar.consultaCepApi.model.Endereco;

public interface EnderecoService {
	
	Endereco obterPorCep(String cep);
}
