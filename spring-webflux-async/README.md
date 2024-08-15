## Spring Boot Asynchronous Communication with PostGres
- Spring boot for asynchronous communication by using webflus with PostGreSQL.
- Inportant Notes: 
* Swagger is important part in API, however, we have an issue with WebFlux and Swagger (2 or 3) in Springboot few versions such 2.7.x
* WebFlux: Spring WebFlux is a reactive programming framework within the Spring ecosystem for building asynchronous, non-blocking, and event-driven web applications.
* PostGreSQL: PostgreSQL's main benefit is it's high data consistency and integrity, but it also encapsulates four important features: Atomicity, Consistency, Isolation, Durability (known as ACID), which ensure the transactions remain correct and reliable in DBMS during the process of writing or updating data.

# Dependencies
- The REST API has been built with Spring boot with PostGreDb
- Project: Maven
- Application Framework: Spring Boot (2.4.4)
- Language: Java (8)
- Packaging: Jar
- Dependencies (Spring Web, Lombok, Spring Data JPA, Spring Devtools, Spring Test, junit, Springfox(Swagger2), WebFlux), PostGreSQL.
- Change application.properties for DB connection attributes (db name, user and password) when required.

## Local setup
- The application needs postgres to run. There is just one table for which the create SQL query is mentioned in 
`src/main/resources/data.sql`. 
- About PostGresSQL: You might use pgAdmin UI tool to connect to your PostgreSQL (DB). And just connect to pgAdmin UI add foloowing SQL:
`CREATE TABLE VEHICLE (
  id SERIAL PRIMARY KEY,
  model VARCHAR(250) NOT NULL,
  make VARCHAR(250) NOT NULL,
  vin VARCHAR(250) NOT NULL,
  color VARCHAR(250) NOT NULL,
  year  VARCHAR(250) NOT NULL);`
  
- Compile the application with `mvn clean install`

- Run the application with `mvn spring-boot:run`

## Swagger
- The application has swagger 2 integrated. Access the swagger at

[Swagger Local URL](http://localhost:8089/swagger-ui.html)
