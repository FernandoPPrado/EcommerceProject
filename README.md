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
|  |  | 
|  |  | 
|  |  | 
|  |  | 
|  |  | 
|  |  | 
|  |  | 
|  |  | 
|  |  | 
|  |  | 



📖 Endpoints
🔑 Autenticação (/auth)
|  |  |  |  | 
|  | /auth/login |  |  | 
|  | /auth/create |  |  | 



👤 Usuários (/user)
|  |  |  |  | 
|  | /user/update/{id} |  |  | 
|  | /user/delete/{id} |  |  | 
|  | /user/me |  |  | 



🛒 Produtos (/product)
|  |  |  |  | 
|  | /product/create |  |  | 
|  | /product/delete/{id} |  |  | 
|  | /product/update/{id} |  |  | 
|  | /product/get/{id} |  |  | 
|  | /product/getall |  |  | 



💳 Pagamentos (/payments)
|  |  |  |  | 
|  | /payments |  |  | 
|  | /payments/webhook |  |  | 



📦 Compras (/purchase)
|  |  |  |  | 
|  | /purchase/create |  |  | 
|  | /purchase/me |  |  | 
|  | /purchase/getall |  |  | 
|  | /purchase/get/{id} |  |  | 
|  | /purchase/update/{id} |  |  | 
|  | /purchase/delete/{id} |  |  | 



🔒 Usuário de Teste
Para testar rapidamente:
- username: UsuarioTeste
- senha: 123456


