CREATE TABLE TBCATEGORIA(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(100) NOT NULL
);


CREATE TABLE TBPRODUTO (
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(100) NOT NULL,
	DESCRICAO VARCHAR(1000) NOT NULL,
	DT_CRIACAO DATETIME NOT NULL,
	SCORE FLOAT,
	ID_CATEGORIA BIGINT NOT NULL,
	FOREIGN KEY(ID_CATEGORIA) REFERENCES TBCATEGORIA(ID)
);

CREATE TABLE TBCOMPRADOR(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(150) NOT NULL
);

CREATE TABLE TBVENDEDOR(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(150) NOT NULL
);

CREATE TABLE TBVENDA(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	ID_VENDEDOR BIGINT NOT NULL,
	ID_COMPRADOR BIGINT NOT NULL,
	ID_PRODUTO BIGINT NOT NULL,
	FOREIGN KEY(ID_VENDEDOR) REFERENCES TBVENDEDOR(ID),
	FOREIGN KEY(ID_COMPRADOR) REFERENCES TBCOMPRADOR(ID),
	FOREIGN KEY(ID_PRODUTO) REFERENCES TBPRODUTO(ID)
);

CREATE TABLE TBAVALIACAO(
	ID_VENDA BIGINT PRIMARY KEY  NOT NULL,
	DT_REGISTRO DATETIME NOT NULL,
	NOTA TINYINT NOT NULL,
	FOREIGN KEY(ID_VENDA) REFERENCES TBVENDA(ID)
);