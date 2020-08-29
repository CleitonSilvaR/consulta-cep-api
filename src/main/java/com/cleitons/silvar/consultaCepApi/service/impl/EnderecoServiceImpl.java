package com.cleitons.silvar.consultaCepApi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleitons.silvar.consultaCepApi.model.entity.Endereco;
import com.cleitons.silvar.consultaCepApi.repository.EnderecoRepository;
import com.cleitons.silvar.consultaCepApi.service.EnderecoService;
import com.cleitons.silvar.consultaCepApi.util.SistemaConstantes;
import com.cleitons.silvar.consultaCepApi.util.Util;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	private Endereco obterPorCep(String cep) {
		Optional<Endereco> enderecoOptional = enderecoRepository.findByCep(cep);
		
		if (enderecoOptional.isPresent()) {
			return enderecoOptional.get();
		}
		
		return null;
	}
	
	@Override
	public Endereco consultarEndereco(String cep) {
		Endereco enderecoEncontrado = null;
		
		cep = Util.preencherFimZeros(cep, SistemaConstantes.OITO);
		
		enderecoEncontrado = obterPorCep(cep);
		
		if (enderecoEncontrado == null) {
			for (int i=SistemaConstantes.ZERO; i<SistemaConstantes.OITO; i++) {
				cep = Util.substituirUltimoCaractereNumerio(cep, '0');
				
				if (!"00000000".equals(cep)) {
					enderecoEncontrado = obterPorCep(cep);
					
					if (enderecoEncontrado != null) {
						break;
					}
				} else {
					break;
				}
			}
		}
		
		return enderecoEncontrado;
	}
}
