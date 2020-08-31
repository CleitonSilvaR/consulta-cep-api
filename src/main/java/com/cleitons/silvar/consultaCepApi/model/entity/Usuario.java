package com.cleitons.silvar.consultaCepApi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.cleitons.silvar.consultaCepApi.util.SistemaConstantes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name = "usuario", schema = "public")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Usuario", description = "Objeto com valores de usuario para cadastro")
public class Usuario {

	@Id
	@JsonIgnore
	@SequenceGenerator(name = "usuarioSeq", sequenceName = "usuario_id_multi_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuarioSeq")
	@Column(unique = true, nullable = false)
	@Getter private Long id;
	
	@ApiModelProperty(value = "login", required = true)
	@NotEmpty( message = "Campo Login é obrigatório.")
	@Column(name = "login", length = SistemaConstantes.CINQUENTA)
	@Getter @Setter private String login;
	
	@ApiModelProperty(value = "login", required = true)
	@NotEmpty( message = "Campo Senha é obrigatório.")
	@Column(name = "senha", columnDefinition = "TEXT")
	@Getter @Setter private String senha;
	
}
