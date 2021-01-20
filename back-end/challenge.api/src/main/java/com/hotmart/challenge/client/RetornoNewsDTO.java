package com.hotmart.challenge.client;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RetornoNewsDTO implements Serializable {

	private static final long serialVersionUID = -1963916411485769978L;

	private Long totalResults;

}
