# 🚗 Parking Control API

API REST para gerenciamento de vagas de estacionamento em condomínios, desenvolvida com Spring Boot.

## 📌 Funcionalidades

- Cadastro de vagas de estacionamento
- Listagem paginada de vagas
- Busca por ID
- Atualização de dados
- Exclusão de registros
- Validações de regras de negócio:
  - Placa única
  - Vaga única
  - Apartamento + bloco único

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

## ⚙️ Configuração do projeto

### Pré-requisitos

- Java instalado
- MySQL rodando
- Maven (ou usar wrapper)

---

### 🔧 Configuração do banco

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost/parking-controlDb?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ Como executar
mvn spring-boot:run

Ou rodar pela sua IDE.

📡 Endpoints
➤ Criar vaga

POST /parking-spot

➤ Listar vagas

GET /parking-spot

➤ Buscar por ID

GET /parking-spot/{id}

➤ Atualizar vaga

PUT /parking-spot/{id}

➤ Deletar vaga

DELETE /parking-spot/{id}

📥 Exemplo de requisição
{
  "parkingSpotNumber": "A1",
  "licensePlateCar": "ABC1234",
  "brandCar": "Toyota",
  "modelCar": "Corolla",
  "colorCar": "Preto",
  "responsableName": "João",
  "apartment": "101",
  "block": "A"
}

⚠️ Tratamento de erros

A API possui tratamento global de exceções com:

BusinessException

GlobalExceptionHandler

Retornando mensagens claras para o cliente.

📁 Estrutura do projeto
controller/
service/
repository/
entity/
dtos/
exceptions/


