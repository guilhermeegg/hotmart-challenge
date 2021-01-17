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

## Banco de dados
Optei por utilizar um banco de dados em mémoria através do H2 e o Liquibase para controle de versão e carga dos scripts
A imagem do diagrama de entidade e relacionamento está na raiz do projeto (Diagrama_entidade_relacionamento_v1.png)
As configurações estão no arquivo do projeto resources/application.properties
Para acessar o console do H2 basta utilizar a url  http://<host>:<porta>/h2/ -EX: http://localhost:8080/h2/ 