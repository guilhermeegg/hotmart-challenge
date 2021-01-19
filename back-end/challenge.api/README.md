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
A imagem do diagrama de entidade e relacionamento está na raiz do projeto (Diagrama_entidade_relacionamento_v1.png)
As configurações estão no arquivo do projeto resources/application.properties
Para acessar o console do H2 basta utilizar a url  http://<host>:<porta>/h2 -EX: http://localhost:8080/h2


#Evolução da aplicação 
Criar um Crud(create, read, update, delete) de produtos.
Criar uma API de produtos e disponibilizar as operações de crud (find, delete).
Documentação de API através do Swagger
