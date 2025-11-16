<h1 align="center">
  Frontier API
</h1>

API construída para gerenciamento de pessoas, empregadores e folhas de pagamento.

## Sumário
- [Tecnologias](#tecnologias)
- [Como executar](#como-executar)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
- [Migrations e Dados Fictícios](#migrations-e-dados-fictícios)
- [Documentação da API](#documentação-da-api)
- [Práticas Adotadas](#práticas-adotadas)
- [Estrutura de Pastas](#estrutura-de-pastas)

## Tecnologias
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Lombok](https://projectlombok.org/features/)
- [Flyway](https://documentation.red-gate.com/flyway)
- [Health Check](https://docs.spring.io/spring-boot/api/rest/actuator/health.html)
- Docker & Docker Compose

## Como executar

### 1. Pré-requisitos
- Docker e Docker Compose instalados
- Java 17 ou superior
- Maven

### 2. Subindo o banco de dados
- Na raiz do projeto, execute:
  ```bash
  docker-compose up
  ```
- O container PostgreSQL será iniciado automaticamente com o banco `frontier`.
- Todos os scripts de migração em `src/main/resources/db/migration` serão executados, criando o schema, tabelas e populando com dados fictícios.
- O banco estará disponível em `localhost:5432` (usuário: root, senha: root).

### 3. Executando a aplicação
- Compile e rode a aplicação com Maven:
  ```bash
  ./mvnw spring-boot:run
  ```
- Alternativamente, execute a classe principal `FrontierApplication.java` pela sua IDE.
- A aplicação estará disponível em `http://localhost:8080`.

### 4. Acessando a documentação e monitoramento
- **Swagger:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Health Check:** [http://localhost:8080/monitor/health](http://localhost:8080/monitor/health)

### 5. Observações
- Caso queira reiniciar o banco e os dados, pare o container, remova o volume e suba novamente:
  ```bash
  docker-compose down -v && docker-compose up
  ```
- As configurações de conexão podem ser ajustadas em `src/main/resources/application.yml`.

## Configuração do Banco de Dados
- O banco é criado automaticamente pelo Docker Compose.
- O schema `frontier` é criado via script de migração (`00_create_schema.sql`).
- As tabelas e dados fictícios são populados via Flyway.
- Configurações podem ser ajustadas em `src/main/resources/application.yml`.

## Migrations e Dados Fictícios
- Scripts de migração estão em `src/main/resources/db/migration`.
- O arquivo `V7__populate_tables.sql` insere dados fictícios para testes.
- Flyway executa todos os scripts automaticamente ao iniciar a aplicação.

## Documentação da API
- A documentação Swagger é gerada automaticamente e pode ser acessada em:
  - `http://localhost:8080/swagger-ui.html` (após iniciar a aplicação)
- Endpoints disponíveis para gerenciamento de usuários, empregadores, funcionários e folhas de pagamento.

## Práticas Adotadas
- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Status do serviço com Health checks
- Migrations com Flyway

## Estrutura de Pastas
```
src/main/java/br/com/frontier/
  core/           # Núcleo da aplicação (segurança, JWT, etc)
  employee/       # Lógica de funcionários
  employer/       # Lógica de empregadores
  payroll/        # Lógica de folha de pagamento
  user/           # Lógica de usuários
  shared/         # Classes compartilhadas
src/main/resources/
  db/migration/   # Scripts de migração Flyway
  application.yml # Configuração principal
```

## Contato
Dúvidas ou sugestões? Abra uma issue ou envie um e-mail para o mantenedor do projeto.
