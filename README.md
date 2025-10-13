# 🚀 EcommerceProject

## 🌐 Visão Geral

[👉 Acesse aqui](https://gallant-mindfulness-production.up.railway.app/)

API REST em **Spring Boot** com:
- 🔒 Autenticação via JWT  
- 💳 Integração com MercadoPago  
- 📄 Documentação interativa com Swagger (Springdoc OpenAPI)  
- 🐳 Docker & Docker Compose para execução local  
- ☁️ Deploy automático no Railway com HTTPS  
- ⏱️ Timer de tentativas de login com backoff exponencial  

---

## 🛠️ Tecnologias

| Tecnologia | Descrição |
|------------|-----------|
| ☕ Java 17 | Linguagem principal |
| 📦 Spring Boot 3.x | Framework para microserviços |
| 🔒 Spring Security + JWT | Autenticação e autorização |
| 🗄️ Spring Data JPA | Persistência de dados |
| 📋 Springdoc OpenAPI | Documentação Swagger |
| 🧩 Maven | Gerenciamento de dependências |
| 🐳 Docker & Docker Compose | Containerização |
| ☁️ Railway | Deploy em nuvem |
| 💳 MercadoPago SDK | Integração de pagamentos |

---

## 📖 Endpoints

### 🔑 Autenticação (`/auth`)

| Método | Endpoint       | Descrição                  | Auth |
|--------|----------------|----------------------------|------|
| POST   | `/auth/login`  | 🗝️ Retorna JWT             | ❌   |
| POST   | `/auth/create` | ✍️ Registra novo usuário   | ❌   |

---

### 👤 Usuários (`/user`)

| Método | Endpoint             | Descrição                        | Auth            |
|--------|----------------------|----------------------------------|-----------------|
| PUT    | `/user/update/{id}`  | 📝 Atualiza usuário              | ✅ (self/admin) |
| DELETE | `/user/delete/{id}`  | 🗑️ Remove usuário                | ✅ (self/admin) |
| GET    | `/user/me`           | 👤 Dados do usuário logado       | ✅              |

---

### 🛒 Produtos (`/product`)

| Método | Endpoint               | Descrição                  | Auth   |
|--------|------------------------|----------------------------|--------|
| POST   | `/product/create`      | ➕ Cria produto             | ✅ admin |
| DELETE | `/product/delete/{id}` | ➖ Remove produto           | ✅ admin |
| PUT    | `/product/update/{id}` | ✏️ Atualiza produto         | ✅ admin |
| GET    | `/product/get/{id}`    | 🔍 Busca produto por ID     | ✅ admin |
| GET    | `/product/getall`      | 📜 Lista todos os produtos  | ✅ user |

---

### 💳 Pagamentos (`/payments`)

| Método | Endpoint            | Descrição                                | Auth |
|--------|---------------------|------------------------------------------|------|
| POST   | `/payments`         | 💰 Cria pagamento via MercadoPago        | ✅   |
| POST   | `/payments/webhook` | 🔄 Webhook MercadoPago (atualiza status) | ❌   |

---

### 📦 Compras (`/purchase`)

| Método | Endpoint                  | Descrição                  | Auth   |
|--------|---------------------------|----------------------------|--------|
| POST   | `/purchase/create`        | 🛒 Cria nova compra        | ✅ admin |
| GET    | `/purchase/me`            | 📋 Compras do usuário      | ✅ user |
| GET    | `/purchase/getall`        | 📑 Lista todas as compras  | ✅ admin |
| GET    | `/purchase/get/{id}`      | 🔎 Busca compra por ID     | ✅ admin |
| PUT    | `/purchase/update/{id}`   | ♻️ Atualiza compra         | ✅ admin |
| DELETE | `/purchase/delete/{id}`   | ❌ Remove compra           | ✅ admin |

---

## 🔒 Usuário de Teste

Para testar rapidamente:  
- **username:** `UsuarioTeste`  
- **senha:** `123456`  

---

## ⚙️ Configuração de Ambiente

1. 📝 Copiar o arquivo de exemplo:  
   cp application.properties.example application.properties

2. 🔒 Preencher em application.properties:

- DB_HOST, DB_PORT, DB_NAME, DB_USERNAME, DB_PASSWORD

- MERCADO_ACCESS_TOKEN

- SERVER_PORT

▶️ Executar:

- mvn clean spring-boot:run

🐳 (Opcional) Docker Compose:

- docker-compose up --build

📄 Documentação Swagger

[👉 Acesse aqui](https://gallant-mindfulness-production.up.railway.app/swagger-ui/index.html)

📝 Licença

MIT License
