# Hotmart Challenge API Backend

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


## Documentação das APIs
Optei por utilizar o Swagger para documentar as APIs. 
Para acessar o Swagger basta utilizar a url  http://<host>:<porta>/swagger-ui.html -EX: http://localhost:8080/swagger-ui.html

## Banco de dados
Optei por utilizar um banco de dados em mémoria através do H2 e o Liquibase para controle de versão e carga dos scripts
A imagem do diagrama de entidade e relacionamento está na raiz do projeto (Diagrama_entidade_relacionamento_v3.png)
As configurações estão no arquivo do projeto resources/application.properties
Para acessar o console do H2 basta utilizar a url  http://<host>:<porta>/h2 -EX: http://localhost:8080/h2


#Evolução da aplicação 
Criar um Crud(create, read, update, delete) de produtos.
Criar uma API de produtos e disponibilizar as operações de crud (find, delete, insert, update, list).
Documentação de API através do Swagger
Buscar e armazenar a quantidade de notícias por categoria (JOB) - ServicoQuantidadeNoticiasScheduled (Executado se necessário e de 6 em 6 horas) 
Calcular e armazenar o score dos produtos (JOB) - ServicoScoreProdutoScheduled (Executado após o start da aplicação e de hora em hora)
Criar uma API para retornar o ranqueamento dos produtos


## como rodar o código

1. Clonar o repositório git
https://github.com/guilhermeegg/hotmart-challenge.git

2. Instalar as dependências do projeto
mvn clean install

3. Pelo IDE (Eclipse) basta executar a classe:
ChallengeApiApplication.java

Também pode empacotar o aplicativo
	3.1 Gerar o pacote mvn package
	3.2 Executar java –jar ../target/challenge-api-1.0.0-SNAPSHOT.jar


