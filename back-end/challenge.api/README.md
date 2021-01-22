# Hotmart Challenge API Backend

##Descrição da aplicação 
	-API de produto para as operações de crud (list, find, delete, update, insert).
	-Serviço e API para buscar os produtos ranqueados.
	-Documentação das APIs através do Swagger
	-Scheduler para buscar e armazenar a quantidade de notícias por categoria - ServicoQuantidadeNoticiasScheduled (Executado se necessário e de 6 em 6 horas) 
	-Scheduler para buscar e armazenar o score dos produtos - ServicoScoreProdutoScheduled (Execução imediata após o start da aplicação e de hora em hora)
	-Auditoria e Paginação


## Tecnologias Utilizadas
* Java 11
* Maven
* Spring Boot
* library lombok https://projectlombok.org/
* H2
* Liquibase
* Java Bean Validation
* Spring Data JPA
* JUnit
* library modelmapper http://modelmapper.org/
* Swagger através da libary SpringFox para documentar as APIs
* Hibernate Envers para auditoria

## Documentação das APIs
	-Optei por utilizar o Swagger para documentar as APIs. 
	-Para acessar o Swagger basta utilizar a url  http://<host>:<porta>/swagger-ui.html -EX: http://localhost:8080/swagger-ui.html

## Banco de dados
	-Optei por utilizar um banco de dados em mémoria através do H2 e o Liquibase para controle de versão e carga dos scripts.
	-A imagem do diagrama de entidade e relacionamento está na raiz do projeto (Diagrama_entidade_relacionamento_v4.png)
	-As configurações estão no arquivo do projeto resources/application.properties
	-Para acessar o console do H2 basta utilizar a url  http://<host>:<porta>/h2 -EX: http://localhost:8080/h2



## como rodar o projeto

1) Clonar o repositório git https://github.com/guilhermeegg/hotmart-challenge.git

2) Instalar as dependências do projeto mvn clean install

3) Pelo IDE (Eclipse) basta executar a classe: ChallengeApiApplication.java
	3.1 Também pode empacotar o aplicativo
		3.1.1 Gerar o pacote mvn package
		3.1.2 Executar java –jar ../target/challenge-api-1.0.0-SNAPSHOT.jar


4) Os serviços estão documentados no Swagger http://localhost:8080/swagger-ui.html - Exemplo de endpoint: GET http://localhost:8080/challege-api/v1/produtos/1