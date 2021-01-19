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
@ApiModel(description = "Dados da categoria request")
public class CategoriaRequestDTO implements Serializable {

	private static final long serialVersionUID = 4247486026281063252L;

	@ApiModelProperty(position = 1, required = true, notes = "Identificação da categoria")
	@NotNull
	private Long id;

}
