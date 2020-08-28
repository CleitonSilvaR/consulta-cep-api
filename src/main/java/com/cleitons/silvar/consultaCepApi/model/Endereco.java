package com.cleitons.silvar.consultaCepApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cleitons.silvar.consultaCepApi.util.SistemaConstantes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name = "endereco", schema = "cep")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	@Id
	@JsonIgnore
	@SequenceGenerator(name = "enderecoSeq", sequenceName = "endereco_id_multi_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "enderecoSeq")
	@Column(unique = true, nullable = false)
	@Getter private Long id;
	
	@Column(name = "cep", length = SistemaConstantes.OITO)
	@Getter @Setter private String cep;
	
	@Column(name = "rua", length = SistemaConstantes.DUZENTOS_CINQUENTA)
	@Getter @Setter private String rua;
	
	@Column(name = "bairro", length = SistemaConstantes.DUZENTOS_CINQUENTA)
	@Getter @Setter private String bairro;
	
	@Column(name = "cidade", length = SistemaConstantes.CEM)
	@Getter @Setter private String cidade;
	
	@Column(name = "estado", length = SistemaConstantes.DOIS)
	@Getter @Setter private String estado;
}