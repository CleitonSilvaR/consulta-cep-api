package com.cleitons.silvar.consultaCepApi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleitons.silvar.consultaCepApi.model.Endereco;
import com.cleitons.silvar.consultaCepApi.repository.EnderecoRepository;
import com.cleitons.silvar.consultaCepApi.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public Endereco obterPorCep(String cep) {
		Optional<Endereco> enderecoOptional = enderecoRepository.findByCep(cep);
		
		if (enderecoOptional.isPresent()) {
//			throw new Exception("Erro!");
			return enderecoOptional.get();
		}
		
		return new Endereco();
	}
	
}
