package com.hotmart.challenge.web.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = "id")
@ApiModel(description = "Dados da categoria response")
public class CategoriaResponseDTO implements Serializable {

	private static final long serialVersionUID = -144237564144349135L;

	@ApiModelProperty(position = 1, required = true, notes = "Identificação da categoria")
	@NotNull
	private Long id;

	@ApiModelProperty(position = 1, required = true, notes = "Nome da categoria")
	private String nome;

}
