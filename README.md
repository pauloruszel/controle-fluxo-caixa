# 🎯 API Fluxo Caixa
Este é um projeto de API RESTful para gerenciamento de fluxo de caixa de uma empresa. Com ele, é possível cadastrar lançamentos de crédito e débito e verificar o saldo diário.

## 🔨 Desenho da arquitetura
![api-fluxo-caixa drawio](https://user-images.githubusercontent.com/12766450/236968499-3a6b8f77-1b4e-43ab-bc54-f95e3e5b837c.png)

## Padrões de microserviço utilizados
* Separação em camadas (Controller, Service e Repository)
* Injeção de dependências com Spring
* Uso de DTOs para transferência de dados entre a API e o banco de dados

## Padrões de projetos utilizados
- `Strategy`: utilizado para implementar diferentes formas de cálculo do saldo diário.
- `Repository`: utilizado para abstrair a camada de acesso ao banco de dados.
- `DTO`: utilizado para transferência de dados entre as camadas.


## ✔️ Tecnologias e bibliotecas usadas
- ``Java 17``
- ``Spring Boot 3``
- ``Spring Data JPA``
- ``Spring Security``
- ``Spring Boot Actuator``
- ``JWT``
- ``H2 Database``
- ``Swagger 3``
- ``Lombok``
- ``JUnit 5``
- ``Mockito``
- ``ModelMapper``

## Endpoints
- `POST /login`: endpoint para realizar a autenticação de um usuário e gerar um token JWT.
- `POST /lancamentos`: endpoint para adicionar um novo lançamento.
- `GET /lancamentos/{id}`: endpoint para buscar um lançamento por id.
- `GET /lancamentos`: endpoint para listar todos os lançamentos.
- `GET /lancamentos?data={data}`: endpoint para listar todos os lançamentos de uma determinada data.
- `GET /saldo-consolidado/{data}`: endpoint para calcular o saldo diário de uma determinada data.

## 🛠️ Execução do projeto
Para executar o projeto, é necessário ter o Java 17 e o Maven instalados.

## 🚀 Como usar
Para usar a API, é necessário ter o Docker e o Docker Compose instalados.
## Clone o repositório:

```
git clone https://github.com/pauloruszel/controle-fluxo-caixa.git
```
## 📁 Entre na pasta do projeto:
```
cd controle-fluxo-caixa
```
### Execute o comando abaixo para compilar e empacotar o projeto:
```bash
mvn clean package
```
## 🐳 Execute o docker-compose:
```bash
docker-compose up --build
```
A API estará disponível em http://localhost:8080.

## 🔑 Autenticação por token
Para utilizar as funcionalidades da API, é necessário realizar a autenticação e obter um token JWT.

Endpoint de autenticação:
POST /login
```
Body:
{
    "login": "Paulo",
    "password": "1234"
}
```

A resposta será um token JWT, que deve ser incluído no header das requisições que exigem autenticação, no formato "Bearer {token}".
## Testes unitários
Para rodar os testes unitários, execute o comando abaixo:

```
mvn test
```
## Observability
A aplicação possui o Spring Boot Actuator configurado para expor os endpoints /health, /info e /metrics na porta 1979. 
Para acessá-los, utilize o seguinte endereço: 
* http://localhost:1979/actuator/health.
