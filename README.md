# 🛒 Sistema de E-Commerce - API REST com Spring Boot

## 📋 Sobre o Projeto

Este projeto é uma API REST completa para um sistema de e-commerce desenvolvida durante o curso **"Java COMPLETO"** do professor **Dr. Nelio Alves** na Udemy. O projeto implementa um sistema de gerenciamento de pedidos, produtos, categorias e usuários, seguindo as melhores práticas de desenvolvimento backend com Spring Boot.

### 👨‍💻 Sobre o Autor

**Kleber Santos** - Recém-formado em Análise e Desenvolvimento de Sistemas, em busca da primeira oportunidade como desenvolvedor backend júnior.

Este projeto representa o meu aprendizado prático em desenvolvimento backend com Java e Spring Boot, onde além de seguir o curso, implementei melhorias e funcionalidades adicionais para demonstrar o meu conhecimento e capacidade de evoluir além do conteúdo básico.

---

## 🎯 Objetivos do Curso

Este projeto foi desenvolvido seguindo o curso que tinha como objetivos:

- ✅ Criar projeto Spring Boot Java
- ✅ Implementar modelo de domínio
- ✅ Estruturar camadas lógicas: resource, service, repository
- ✅ Configurar banco de dados de teste (H2)
- ✅ Povoar o banco de dados
- ✅ CRUD completo (Create, Retrieve, Update, Delete)
- ✅ Tratamento de exceções

---

## 🚀 O que foi Implementado Seguindo o Curso

### 1. **Modelo de Domínio**

- **User** - Entidade de usuários/clientes
- **Order** - Entidade de pedidos
- **Product** - Entidade de produtos
- **Category** - Entidade de categorias
- **OrderItem** - Entidade de itens do pedido (associação many-to-many com atributos extras)
- **Payment** - Entidade de pagamento (associação one-to-one com Order)
- **OrderStatus** - Enum para status dos pedidos

### 2. **Camadas da Aplicação**

- **Resource Layer** - Controllers REST com endpoints HTTP
- **Service Layer** - Lógica de negócio
- **Repository Layer** - Acesso a dados com JPA

### 3. **Funcionalidades CRUD**

- CRUD completo para **User**

### 4. **Banco de Dados**

- Configuração do H2 Database (banco em memória para testes)
- Mapeamento JPA/Hibernate

### 5. **Tratamento Global de Exceções**

- `ResourceNotFoundException` - Recurso não encontrado
- `DatabaseException` - Erros de integridade do banco
- `ResourceExceptionHandler` - Handler global de exceções
- `StandardError` - Padrão de resposta de erro

---

# 🚀 Evolução do Projeto (Implementações Próprias)

## 🥇 1ª Evolução — PostgreSQL + Docker

Primeira melhoria estrutural:

- Substituição do banco em memória por **PostgreSQL**
- Configuração de múltiplos perfis (default e dev)
- Containerização com Docker
- Orquestração com Docker Compose

Objetivo: aproximar o projeto de um ambiente real de backend.

---

## 🥈 2ª Evolução — Flyway (Migrations Versionadas)

Implementação de versionamento de banco com **Flyway**:

- Criação de migrations
- Controle de versionamento de schema
- Separação entre estrutura e seed de dados
- Controle de evolução do banco

Objetivo: aplicar prática comum em projetos profissionais.

---

## 🥉 3ª Evolução — Autenticação e Autorização (JWT + RBAC)

Implementação de segurança com **Spring Security**.

### 🔐 Recursos implementados:

- Autenticação stateless com JWT
- Criptografia de senha com BCrypt
- Controle de acesso baseado em roles (RBAC):
  - ROLE_USER
  - ROLE_ADMIN
- Usuário ADMIN criado via migration
- Proteção de endpoints por perfil
- Tratamento de AuthenticationException
- Tratamento de AccessDeniedException

A aplicação utiliza:

```java
session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
```

Sem uso de sessão HTTP, validando o token a cada requisição via filtro personalizado.

Objetivo: elevar o projeto ao nível de backend seguro e controlado por perfis.

---

### 🔄 State Pattern (Pedidos)

Implementação do padrão **State** para gerenciar o ciclo de vida dos pedidos de forma mais robusta e seguindo princípios SOLID:

- **OrderState** - Interface para estados do pedido
- **WaitingPaymentState** - Estado inicial (aguardando pagamento)
- **PaidState** - Estado após pagamento
- **ShippedState** - Estado após envio
- **DeliveredState** - Estado após entrega
- **CanceledState** - Estado cancelado

**Transições controladas via endpoints específicos:**

- `PUT /orders/{id}/pay` - Pagar pedido
- `PUT /orders/{id}/cancel` - Cancelar pedido
- `PUT /orders/{id}/ship` - Enviar pedido
- `PUT /orders/{id}/deliver` - Entregar pedido
  
**Benefícios:**

- Transições de estado controladas e validadas
- Prevenção de transições inválidas
- Código aberto para extensão (Open/Closed Principle)
- Regras de negócio encapsuladas

---

### 📦 DTO Pattern

- RequestDTO
- ResponseDTO
- SummaryDTO
- DetailsDTO

Benefícios:
- Controle sobre dados expostos
- Evita serialização circular
- Maior segurança
- Diferentes níveis de detalhamento

---

### 🧪 Validações e Regras de Negócio

Implementei validações usando Bean Validation e validações customizadas:

## Bean Validation

- @NotNull
- @NotBlank
- @Size
- @Positive
- @Valid

## Validações Customizadas

- NoDuplicateProducts
- UniqueList

## Regras Aplicadas

- Email único para usuário
- Nome único para produto
- Nome único para categoria
- Bloqueio de exclusão com dependências
- Controle de transições inválidas de estado

**Benefícios:**

- Dados sempre consistentes
- Mensagens de erro claras e específicas
- Prevenção de erros de negócio

---

### Melhorias de Integridade de Dados

- Constraints de unicidade no banco (`@UniqueConstraint`)
  - Email único para usuários
  - Nome único para produtos
- Validações de integridade referencial
- Prevenção de exclusão de recursos com dependências

---

### ⚠️Tratamento de Exceções Aprimorado

Expandi o tratamento de exceções com novas exceções de negócio:

- `DuplicateResourceException` - Recurso duplicado (email, nome de produto, etc.)
- `BusinessException` - Regras de negócio violadas
- Tratamento de `MethodArgumentNotValidException` - Erros de validação
- Handler genérico para exceções não tratadas
- Respostas de erro padronizadas com detalhes de campos inválidos

---

### 🗄️ Banco de Dados

## Perfil Default
PostgreSQL

## Perfil Dev
H2 em memória

## Versionamento
Flyway para migrations versionadas

---

### 🐳 Containerização

- Dockerfile para aplicação
- Docker Compose para PostgreSQL + API
- Variáveis de ambiente configuráveis

Execução padronizada em qualquer ambiente.

---

## 🛠️ Tecnologias Utilizadas

- **Java 25**
- **Spring Boot 4.0.2**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database** (perfil dev - testes)
- **PostgreSQL** (perfil default - produção)
- **Docker** (containerização)
- **Docker Compose** (orquestração de containers)
- **Maven** (gerenciamento de dependências)
- **SpringDoc OpenAPI** (documentação da API)
- **Bean Validation** (validações)
- **Spring Security**
- **JWT**
- **Flyway**

---

# 📚 Documentação da API

Swagger disponível em:

http://localhost:8080/swagger-ui.html

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 25 (ou superior)
- Maven 3.6+ (ou superior)
- Docker e Docker Compose (para execução com PostgreSQL)

### Opção 1: Executar com PostgreSQL (Perfil Default)

Esta opção utiliza PostgreSQL como banco de dados através do Docker Compose.

1. **Clone o repositório:**

```bash
git clone https://github.com/KleberYuu/workshop-springboot4-jpa.git
cd workshop-springboot4-jpa
```

2. **Compile o projeto:**

```bash
mvn clean package
ou
./mvnw clean package
```

3. **Execute com Docker Compose:**

```bash
docker-compose up --build
```

Isso irá:

- Subir um container PostgreSQL na porta 5432
- Construir e executar a aplicação Spring Boot em um container Docker
- A aplicação estará disponível em `http://localhost:8080`

4. **Acesse a aplicação:**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - Usuário ADMIN criado via migration
     - `email = 'admin@admin.com'`
     - `password = 123456`

**Para parar os containers:**

```bash
docker-compose down
```

### Opção 2: Executar com H2 (Perfil Dev)

Esta opção utiliza H2 (banco em memória) com dados pré-populados, ideal para desenvolvimento rápido.

1. **Clone o repositório:**

```bash
git clone https://github.com/KleberYuu/workshop-springboot4-jpa.git
cd workshop-springboot4-jpa
```

2. **Execute a aplicação com perfil dev:**

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
ou
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

```

3. **Acesse a aplicação:**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: (vazio)
  - Usuário ADMIN criado via migration
     - `email = 'admin@admin.com'`
     - `password = 123456`


---

## 📡 Endpoints da API

### Authentication (`/auth`)

| Método | Endpoint      | Descrição         |
| ------ | ------------- | ----------------- |
| POST   | `/register`   | Cria novo usuário |
| POST   | `/login`      | Fazer login       |

### 👤 Users (`/users`)

| Método | Endpoint      | Descrição               | ROLE_USER | ROLE_ADMIN |
| ------ | ------------- | ----------------------- | --------- | ---------- |
| GET    | `/users`      | Lista todos os usuários | ✅        | ✅        |
| GET    | `/users/{id}` | Busca usuário por ID    | ❌        | ✅        |
| POST   | `/users`      | Cria novo usuário       | ❌        | ✅        |
| PUT    | `/users/{id}` | Atualiza usuário        | ✅        | ✅        |
| DELETE | `/users/{id}` | Deleta usuário          | ❌        | ✅        |

### 📦 Products (`/products`)

| Método | Endpoint         | Descrição               |
| ------ | ---------------- | ----------------------- |
| GET    | `/products`      | Lista todos os produtos |
| GET    | `/products/{id}` | Busca produto por ID    |
| POST   | `/products`      | Cria novo produto       |
| PUT    | `/products/{id}` | Atualiza produto        |
| DELETE | `/products/{id}` | Deleta produto          |

### 🏷️ Categories (`/categories`)

| Método | Endpoint           | Descrição                 |
| ------ | ------------------ | ------------------------- |
| GET    | `/categories`      | Lista todas as categorias |
| GET    | `/categories/{id}` | Busca categoria por ID    |
| POST   | `/categories`      | Cria nova categoria       |
| PUT    | `/categories/{id}` | Atualiza categoria        |
| DELETE | `/categories/{id}` | Deleta categoria          |

### 🛒 Orders (`/orders`)

| Método | Endpoint               | Descrição              |
| ------ | ---------------------- | ---------------------- |
| GET    | `/orders`              | Lista todos os pedidos |
| GET    | `/orders/{id}`         | Busca pedido por ID    |
| POST   | `/orders`              | Cria novo pedido       |
| PUT    | `/orders/{id}/pay`     | Paga pedido            |
| PUT    | `/orders/{id}/cancel`  | Cancela pedido         |
| PUT    | `/orders/{id}/ship`    | Envia pedido           |
| PUT    | `/orders/{id}/deliver` | Entrega pedido         |

---

# 📈 Próximos Passos (Evolução Contínua)

- [x] PostgreSQL + Docker
- [x] Flyway (migrations)
- [x] Autenticação stateless com JWT
- [x] Autorização baseada em roles (RBAC)
- [ ] Testes unitários (Service Layer)
- [ ] Testes de integração (Controller + MockMvc)
- [ ] Pipeline CI com GitHub Actions
- [ ] Paginação e ordenação
- [ ] Cache com Redis
- [ ] Deploy em cloud (AWS)

---

## 📧 Contato

**Kleber Santos**  
Desenvolvedor Backend Java  

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/kleber-santos-577782273/)  
**LinkedIn:** [linkedin.com/in/kleber-santos-577782273](https://www.linkedin.com/in/kleber-santos-577782273/)

---

**Desenvolvido com ❤️ e muito aprendizado!**
