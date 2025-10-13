🚀 EcommerceProject
🌐 Visão Geral
API REST em Spring Boot com:
- 🔒 Autenticação via JWT
- 💳 Integração com MercadoPago
- 📄 Documentação interativa com Swagger (Springdoc OpenAPI)
- 🐳 Docker & Docker Compose para execução local
- ☁️ Deploy automático no Railway com HTTPS
- ⏱️ Timer de tentativas de login com backoff exponencial
🛠️ Tecnologias
- ☕ Java 17
- 📦 Spring Boot 3.x
- 🔒 Spring Security + JWT
- 🗄️ Spring Data JPA (Hibernate)
- 📋 Springdoc OpenAPI (Swagger)
- 🧩 Maven
- 🐳 Docker & Docker Compose
- ☁️ Railway
- 💳 MercadoPago SDK
📖 Endpoints
🔑 Autenticação (/auth)
- POST /auth/login    : 🗝️ retorna JWT (público)
- POST /auth/create   : ✍️ registra usuário (público)
👤 Usuários (/user)
- PUT   /user/update/{id}   : 📝 atualiza usuário (self ou admin)
- DELETE /user/delete/{id}   : 🗑️ remove usuário (self ou admin)
- GET   /user/me            : 👤 dados do usuário logado
🛒 Produtos (/product)
- POST   /product/create       : ➕ cria produto (admin)
- DELETE /product/delete/{id}  : ➖ remove produto (admin)
- PUT    /product/update/{id}  : ✏️ atualiza produto (admin)
- GET    /product/get/{id}     : 🔍 busca por ID (admin)
- GET    /product/getall       : 📜 lista todos (usuário logado)
💳 Pagamentos (/payments)
- POST  /payments          : 💰 cria pagamento via MercadoPago
- POST  /payments/webhook  : 🔄 webhook MercadoPago (sem auth)
📦 Compras (/purchase)
- POST   /purchase/create       : 🛒 cria compra (admin)
- GET    /purchase/me           : 📋 compras do usuário
- GET    /purchase/getall       : 📑 todas as compras (admin)
- GET    /purchase/get/{id}     : 🔎 compra por ID (admin)
- PUT    /purchase/update/{id}  : ♻️ atualiza compra (admin)
- DELETE /purchase/delete/{id}  : ❌ remove compra (admin)
🔒 Usuário de Teste
- username: UsuarioTeste
- senha   : 123456
⚙️ Configuração de Ambiente
- 📝 Copiar o exemplo:
cp application.properties.example application.properties
- 🔒 Preencher em application.properties:
• DB_HOST, DB_PORT, DB_NAME, DB_USERNAME, DB_PASSWORD
• MERCADO_ACCESS_TOKEN
• SERVER_PORT
- ▶️ Executar: mvn clean spring-boot:run
- 🐳 (Opcional) Docker Compose: docker-compose up --build
📄 Documentação Swagger
https://meuapp-production-8692.up.railway.app/swagger-ui.html
📝 Licença
MIT License
