<h1 align="center">
  Frontier API
</h1>

API construido para gerenciamento de pessoas.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Lombok](https://projectlombok.org/features/)
- [Flyway](https://documentation.red-gate.com/flyway)
- [Health Check](https://docs.spring.io/spring-boot/api/rest/actuator/health.html)

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Status do servico com Health checks
- Migrations com Flyway

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/todolist-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).<br>
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).<br>
O Health check poderá ser visualizado em [localhost:8080/monitor/health](http://localhost:8080/monitor/health).

