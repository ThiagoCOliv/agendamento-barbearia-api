# agendamento-barbearia-api

API para gerenciamento de agendamentos em uma barbearia.

## Descrição

Esta API permite realizar operações de CRUD para clientes e agendamentos, além de listar agendamentos por mês. Foi desenvolvida utilizando o ecossistema Spring Boot e segue boas práticas de desenvolvimento.

## Tecnologias Utilizadas

- Java 23
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- MapStruct
- Jakarta Validation

## Configuração

Configure o banco de dados no arquivo `application.yml` ou utilize variáveis de ambiente:
   ```yaml
   spring:
     datasource:
       url: '${DB_URL}'
       username: '${DB_USER}'
       password: '${DB_PASSWORD}'
   ```

## Endpoints

### Clientes

- **POST /clients**: Cria um novo cliente.
- **GET /clients**: Lista todos os clientes.
- **GET /clients/{id}**: Retorna os detalhes de um cliente.
- **PUT /clients/{id}**: Atualiza os dados de um cliente.
- **DELETE /clients/{id}**: Remove um cliente.

### Agendamentos

- **POST /schedules**: Cria um novo agendamento.
- **GET /schedules/{year}/{month}**: Lista os agendamentos de um mês específico.
- **DELETE /schedules/{id}**: Remove um agendamento.
