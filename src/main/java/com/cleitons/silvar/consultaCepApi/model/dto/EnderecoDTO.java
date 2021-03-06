package com.cleitons.silvar.consultaCepApi.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "endereco",  description = "Objeto com valor de cep para consulta")
public class EnderecoDTO {
	
	@ApiModelProperty(value = "cep", required = true)
	private String cep;

}
