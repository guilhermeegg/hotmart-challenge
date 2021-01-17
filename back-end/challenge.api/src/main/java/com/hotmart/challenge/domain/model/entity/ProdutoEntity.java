package com.hotmart.challenge.domain.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBPRODUTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoEntity implements Serializable {

	private static final long serialVersionUID = 7975396979258117137L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(name = "NOME")
	private String nome;

	@NotNull
	@Size(max = 1000)
	@Column(name = "DESCRICAO")
	private String descricao;

	@NotNull
	@Column(name = "DT_CRIACAO")
	private LocalDateTime dataCriacao;

	@Column(name = "SCORE")
	private Float score;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private CategoriaEntity categoria;

}
