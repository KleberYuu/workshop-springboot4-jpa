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

### 5. **Tratamento de Exceções**

- `ResourceNotFoundException` - Recurso não encontrado
- `DatabaseException` - Erros de integridade do banco
- `ResourceExceptionHandler` - Handler global de exceções
- `StandardError` - Padrão de resposta de erro

---

## ✨ Melhorias e Funcionalidades Adicionais (Implementadas Solo)

Além do conteúdo do curso, implementei as seguintes melhorias:

### 1. **CRUD completo para Product, Category e Order**

O curso implementou apenas os endpoints de User. Desenvolvi sozinho toda a API REST para:

- **Product** - Listar, buscar por ID, criar, atualizar e deletar produtos
- **Category** - Listar, buscar por ID, criar, atualizar e deletar categorias
- **Order** - Listar, buscar por ID e criar pedidos

### 2. **Configuração de PostgreSQL e Seed de Dados**

Implementei a configuração completa do PostgreSQL para desenvolvimento e produção:

- Configuração do PostgreSQL como banco padrão (perfil default)
- Configuração do H2 com seed de dados via `data.sql` (perfil dev)
- Arquivo `data.sql` para popular o banco H2 com dados de teste
- Configuração de múltiplos perfis (`application.properties` e `application-dev.properties`)
- Integração com Docker Compose para facilitar setup do PostgreSQL

**Benefícios:**

- Ambiente de desenvolvimento flexível (H2 para testes rápidos, PostgreSQL para desenvolvimento completo)
- Dados de teste pré-populados facilitam desenvolvimento e testes
- Configuração pronta para produção com PostgreSQL
- Facilita onboarding de novos desenvolvedores

### 3. **Padrão State para Gerenciamento de Pedidos**

Implementei o padrão de projeto **State** para gerenciar o ciclo de vida dos pedidos de forma mais robusta e seguindo princípios SOLID:

- **OrderState** - Interface para estados do pedido
- **WaitingPaymentState** - Estado inicial (aguardando pagamento)
- **PaidState** - Estado após pagamento
- **ShippedState** - Estado após envio
- **DeliveredState** - Estado após entrega
- **CanceledState** - Estado cancelado

**Benefícios:**

- Transições de estado controladas e validadas
- Prevenção de operações inválidas (ex: não pode enviar antes de pagar)
- Código mais limpo e manutenível
- Facilita extensão futura de novos estados

**Endpoints adicionais:**

- `PUT /orders/{id}/pay` - Pagar pedido
- `PUT /orders/{id}/cancel` - Cancelar pedido
- `PUT /orders/{id}/ship` - Enviar pedido
- `PUT /orders/{id}/deliver` - Entregar pedido

### 4. **DTOs (Data Transfer Objects)**

Implementei uma camada completa de DTOs para melhor separação de responsabilidades:

- **RequestDTOs** - Para receber dados das requisições
  - `UserRequestDTO`
  - `ProductRequestDTO`
  - `CategoryRequestDTO`
  - `OrderRequestDTO`
  - `OrderItemRequestDTO`

- **ResponseDTOs** - Para retornar dados formatados
  - `UserResponseDTO`
  - `ProductResponseDTO`, `ProductDetailsDTO`, `ProductSummaryDTO`
  - `CategoryResponseDTO`, `CategoryDetailsDTO`, `CategorySummaryDTO`
  - `OrderResponseDTO`
  - `OrderItemResponseDTO`
  - `PaymentResponseDTO`

**Benefícios:**

- Controle sobre quais dados são expostos na API
- Prevenção de serialização circular
- Diferentes níveis de detalhamento (Summary, Details)
- Melhor segurança (não expor campos sensíveis)

### 5. **Validações Robustas**

Implementei validações usando Bean Validation e validações customizadas:

- Validações com `@NotNull`, `@NotEmpty`, `@Positive`, `@Valid`, `@NotBlank`, `@Size`
- **Validação customizada: `NoDuplicateProducts` / `NoDuplicateProductsValidator`**
  - Previne produtos duplicados no mesmo pedido
- **Validação customizada: `UniqueList` / `UniqueListValidator`**
  - Garante que listas não contenham elementos duplicados
  - Usada em `ProductRequestDTO.categoryIds` para evitar a mesma categoria ser associada múltiplas vezes ao produto
- Validação de email único no banco de dados
- Validação de nome único para produtos e categorias

**Benefícios:**

- Dados sempre consistentes
- Mensagens de erro claras e específicas
- Prevenção de erros de negócio

### 6. **Tratamento de Exceções Aprimorado**

Expandi o tratamento de exceções com novas exceções de negócio:

- `DuplicateResourceException` - Recurso duplicado (email, nome de produto, etc.)
- `BusinessException` - Regras de negócio violadas
- Tratamento de `MethodArgumentNotValidException` - Erros de validação
- Handler genérico para exceções não tratadas
- Respostas de erro padronizadas com detalhes de campos inválidos

### 7. **Documentação da API com Swagger/OpenAPI**

Integrei o SpringDoc OpenAPI para documentação automática da API:

- Documentação interativa disponível em `/swagger-ui.html`
- Anotações `@Operation`, `@ApiResponse`, `@Tag` em todos os endpoints
- Esquemas de requisição e resposta documentados
- Facilita testes e integração

### 8. **Melhorias de Integridade de Dados**

- Constraints de unicidade no banco (`@UniqueConstraint`)
  - Email único para usuários
  - Nome único para produtos
- Validações de integridade referencial
- Prevenção de exclusão de recursos com dependências

### 9. **Containerização com Docker**

Implementei containerização completa do projeto:

- **Dockerfile** - Imagem Docker da aplicação Spring Boot
- **docker-compose.yml** - Orquestração de containers (PostgreSQL + API)
- Configuração de variáveis de ambiente para diferentes ambientes
- Facilita deploy e execução em qualquer ambiente

**Benefícios:**

- Ambiente de desenvolvimento consistente
- Fácil configuração de banco de dados
- Pronto para deploy em produção
- Isolamento de dependências

### 10. **Código Limpo e Organizado**

- Separação clara de responsabilidades
- Nomenclatura consistente
- Comentários e documentação inline
- Estrutura de pacotes bem organizada

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

### Dependências Principais

```xml
- spring-boot-starter-webmvc
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- h2 (runtime)
- postgresql (runtime)
- springdoc-openapi-starter-webmvc-ui
```

---

## 📁 Estrutura do Projeto

```
curso/
├── src/
│   ├── main/
│   │   ├── java/com/estudosjava/curso/
│   │   │   ├── config/              # Configurações (OpenApiConfig)
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── category/
│   │   │   │   ├── order/
│   │   │   │   ├── orderItem/
│   │   │   │   ├── product/
│   │   │   │   ├── user/
│   │   │   │   └── validation/      # Validações customizadas
│   │   │   ├── entities/            # Entidades JPA
│   │   │   │   ├── enums/           # Enums (OrderStatus)
│   │   │   │   ├── pk/              # Chaves primárias compostas
│   │   │   │   └── states/          # Implementações do padrão State
│   │   │   ├── repositories/        # Repositórios JPA
│   │   │   ├── resources/           # Controllers REST
│   │   │   │   └── exceptions/      # Handler de exceções
│   │   │   ├── services/            # Camada de serviço
│   │   │   │   └── exceptions/      # Exceções customizadas
│   │   │   └── CursoApplication.java
│   │   └── resources/
│   │       ├── application.properties      # Perfil default (PostgreSQL)
│   │       ├── application-dev.properties # Perfil dev (H2)
│   │       └── data.sql                   # Seed de dados (perfil dev)
│   └── test/
├── Dockerfile                    # Imagem Docker da aplicação
├── docker-compose.yml            # Orquestração PostgreSQL + API
└── pom.xml
```

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
mvn spring-boot:run -Dspring-boot.run.profiles=dev ou ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

```

3. **Acesse a aplicação:**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: (vazio)

O perfil `dev` utiliza H2 em memória e popula automaticamente o banco com dados de teste através do arquivo `data.sql`.

---

## 📡 Endpoints da API

### 👤 Users (`/users`)

| Método | Endpoint      | Descrição               |
| ------ | ------------- | ----------------------- |
| GET    | `/users`      | Lista todos os usuários |
| GET    | `/users/{id}` | Busca usuário por ID    |
| POST   | `/users`      | Cria novo usuário       |
| PUT    | `/users/{id}` | Atualiza usuário        |
| DELETE | `/users/{id}` | Deleta usuário          |

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

## 📚 Documentação da API

A documentação completa da API está disponível através do Swagger UI:

**Acesse:** `http://localhost:8080/swagger-ui.html`

A documentação inclui:

- Descrição de todos os endpoints
- Esquemas de requisição e resposta
- Códigos de status HTTP
- Exemplos de uso
- Possibilidade de testar os endpoints diretamente

---

## 🎓 Aprendizados e Conquistas

### Durante o Curso:

- ✅ Fundamentos de Spring Boot
- ✅ JPA/Hibernate e mapeamento de entidades
- ✅ Arquitetura em camadas (Resource, Service, Repository)
- ✅ CRUD de User
- ✅ Tratamento básico de exceções

### Implementações Próprias:

- ✅ CRUD completo para Product, Category e Order
- ✅ Configuração de PostgreSQL e seed de dados (data.sql)
- ✅ Configuração de múltiplos perfis (default com PostgreSQL, dev com H2)
- ✅ Padrão de projeto State
- ✅ Arquitetura DTO
- ✅ Validações customizadas (NoDuplicateProducts, UniqueList)
- ✅ Documentação com Swagger/OpenAPI
- ✅ Tratamento robusto de exceções
- ✅ Constraints de integridade
- ✅ Containerização com Docker e Docker Compose
- ✅ Código limpo e organizado

---

## 🔄 Próximos Passos (Futuras Melhorias)

- [x] ~~Configurar e testar PostgreSQL em ambiente local~~ ✅ **Concluído** - PostgreSQL configurado com Docker e Docker Compose
- [ ] Autenticação e autorização (JWT)
- [ ] Testes unitários e de integração
- [ ] Paginação e ordenação nas listagens
- [ ] Filtros e busca avançada
- [ ] Upload de imagens para produtos
- [ ] Cache com Redis
- [ ] Logging estruturado
- [ ] Deploy em cloud (Heroku/AWS)

---

## 📝 Licença

Este projeto foi desenvolvido para fins educacionais como parte do curso "Java COMPLETO" do Dr. Nelio Alves.

---

## 🙏 Agradecimentos

- **Dr. Nelio Alves** - Pelo excelente curso e conteúdo didático
- **Udemy** - Pela plataforma de aprendizado

---

## 📧 Contato

**Kleber Santos**  
Recém formado em Análise e Desenvolvimento de Sistemas  
Em busca de oportunidades como desenvolvedor backend júnior

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/kleber-santos-577782273/)  
**LinkedIn:** [linkedin.com/in/kleber-santos-577782273](https://www.linkedin.com/in/kleber-santos-577782273/)

---

**Desenvolvido com ❤️ e muito aprendizado!**
