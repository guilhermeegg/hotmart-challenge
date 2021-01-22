CREATE TABLE REVINFO (
    REV BIGINT PRIMARY KEY AUTO_INCREMENT,
    REVTSTMP BIGINT 
);

CREATE TABLE TBPRODUTO_AUDIT(
	ID BIGINT  NOT NULL,
	NOME VARCHAR(100),
	DESCRICAO VARCHAR(1000),
	DT_CRIACAO DATETIME,
	SCORE FLOAT,
	REV BIGINT,
	REVTYPE SMALLINT,
	ID_CATEGORIA BIGINT,
        PRIMARY KEY (ID, REV),
	FOREIGN KEY(REV) REFERENCES REVINFO (REV)
);