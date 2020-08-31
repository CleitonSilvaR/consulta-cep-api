package com.cleitons.silvar.consultaCepApi.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Error", description = "Objeto com mensagem de erro")
public class ErrorDTO {
	
	private String erro;

}
