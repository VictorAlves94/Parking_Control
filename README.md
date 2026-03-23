🚗 Parking Control API


API REST para gerenciamento de vagas de estacionamento em condomínios, desenvolvida com Spring Boot.


📌 Funcionalidades


✅ Cadastro de vagas de estacionamento
✅ Listagem paginada
✅ Busca por ID
✅ Atualização de dados
✅ Exclusão de registros


🔒 Regras de negócio
🚫 Placa única
🚫 Vaga única
🚫 Apartamento + bloco únicos


🛠️ Tecnologias utilizadas
Java 17
Spring Boot
Spring Data JPA
Hibernate
MySQL
Maven
Swagger (OpenAPI)


⚙️ Configuração do projeto
📋 Pré-requisitos
Java 17 instalado
MySQL rodando
Maven (ou usar o wrapper)
🔧 Configuração do banco


No arquivo application.properties:


spring.datasource.url=jdbc:mysql://localhost/parking-controlDb?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true




▶️ Como executar
mvn spring-boot:run

Ou execute diretamente pela sua IDE.



📡 Documentação da API (Swagger)

Após iniciar o projeto, acesse:

http://localhost:8080/swagger-ui/index.html


A interface permite:


Testar endpoints diretamente
Visualizar requisições e respostas
Ver documentação completa da API
📡 Endpoints
Método	Endpoint	Descrição
POST	/parking-spot	Criar vaga
GET	/parking-spot	Listar vagas
GET	/parking-spot/{id}	Buscar por ID
PUT	/parking-spot/{id}	Atualizar vaga
DELETE	/parking-spot/{id}	Deletar vaga
📥 Exemplo de requisição


{
  "parkingSpotNumber": "A1",
  "licensePlateCar": "ABC1234",
  "brandCar": "Toyota",
  "modelCar": "Corolla",
  "colorCar": "Preto",
  "responsibleName": "João",
  "apartment": "101",
  "block": "A"
}


⚠️ Tratamento de erros



A API possui tratamento global de exceções com:

BusinessException
GlobalExceptionHandler


🔹 Exemplo de erro
{
  "timestamp": "2026-03-23T12:00:00",
  "status": 409,
  "error": "Business Exception",
  "message": "Placa já cadastrada",
  "path": "/parking-spot"
} 


📁 Estrutura do projeto
controller/
service/
repository/
entity/
dtos/
exceptions/


💡 Diferenciais do projeto
✔️ API REST bem estruturada
✔️ Validações com Bean Validation
✔️ Tratamento global de exceções
✔️ Documentação com Swagger
✔️ Paginação com Spring Data
