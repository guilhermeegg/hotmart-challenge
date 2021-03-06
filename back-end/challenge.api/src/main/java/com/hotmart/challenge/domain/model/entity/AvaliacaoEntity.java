package com.hotmart.challenge.domain.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBAVALIACAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AvaliacaoEntity implements Serializable {

	private static final long serialVersionUID = 7220194699155350740L;

	@Id
	@Column(name = "ID_VENDA")
	private Long id;

	@NotNull
	@Column(name = "DT_REGISTRO")
	private LocalDateTime dataRegistro;

	@NotNull
	@Min(0)
	@Max(5)
	@Column(name = "NOTA")
	private Byte nota;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VENDA", insertable = false, updatable = false)
	private VendaEntity venda;

}
