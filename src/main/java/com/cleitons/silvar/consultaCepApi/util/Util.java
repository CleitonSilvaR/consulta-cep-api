package com.cleitons.silvar.consultaCepApi.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Util {
	
	public static String preencherFimZeros(String valor, Integer quantidade) {
		StringBuilder valueRetorno = new StringBuilder();
        if (valor.length() >= quantidade) {
            return valor;
        }
        valueRetorno.append(valor);
        
        for (int i = 0; i < (quantidade - valor.length()); i++) {
            valueRetorno.append("0");
        }
        return valueRetorno.toString();
    }
	
	public static String substituirUltimoCaractereNumerio(String valor, char caracterSelecionado) {
		String caracterSelecionadoString = String.valueOf(caracterSelecionado);
		
		List<String> numeros = Arrays.asList("0","1","2","3","4","5","6","7","8","9").stream()
				.filter(item -> !caracterSelecionadoString.equals(item))
				.collect(Collectors.toList());
		
		StringBuilder retorno = new StringBuilder(valor);
			
		if (retorno.lastIndexOf(caracterSelecionadoString) < SistemaConstantes.ZERO) {
			retorno.setCharAt(retorno.length() -SistemaConstantes.UM, caracterSelecionado);
		} else {
			int posicaoUltimoElemento = -SistemaConstantes.UM;
			for (String numero : numeros) {
				int posicaoUltimoElementoAuxiliar = retorno.lastIndexOf(numero);
				
				if (posicaoUltimoElementoAuxiliar > posicaoUltimoElemento) {
					posicaoUltimoElemento = posicaoUltimoElementoAuxiliar;
				}
			}
			if (posicaoUltimoElemento >= SistemaConstantes.ZERO) {
				retorno.setCharAt(posicaoUltimoElemento, caracterSelecionado);
			}
		}
		
		return retorno.toString();
	}
	
	public static boolean isValorValido(String valor) {
        return (valor != null && !valor.trim().isEmpty());
    }
}
