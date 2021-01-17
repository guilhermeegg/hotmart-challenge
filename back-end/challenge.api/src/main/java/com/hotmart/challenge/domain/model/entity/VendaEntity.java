package com.hotmart.challenge.domain.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBVENDA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class VendaEntity implements Serializable {

	private static final long serialVersionUID = -4423659242062868446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_COMPRADOR")
	private CompradorEntity comprador;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_VENDEDOR")
	private VendedorEntity vendedor;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO")
	private ProdutoEntity produto;

}
