# 🚀 Demo Spring Boot API

API REST desenvolvida em **Spring Boot** com autenticação via **JWT**, integração com **MercadoPago**, documentação com **Swagger** e suporte a **Docker** para execução local e deploy no Railway.

---

## 📌 Tecnologias utilizadas
- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- Spring Data JPA
- Swagger (Springdoc OpenAPI)
- Maven
- Docker
- Railway (deploy com HTTPS automático)
- MercadoPago SDK

---

## 📖 Endpoints da API

### 🔑 Autenticação (`/auth`)
| Método | Endpoint       | Descrição                        | Autenticação |
|--------|----------------|----------------------------------|--------------|
| POST   | `/auth/login`  | Login com usuário e senha, retorna JWT | ❌ |
| POST   | `/auth/create` | Cria novo usuário (registro)     | ❌ |

---

### 👤 Usuários (`/user`)
| Método | Endpoint              | Descrição                                | Autenticação |
|--------|-----------------------|------------------------------------------|--------------|
| PUT    | `/user/update/{id}`   | Atualiza dados do usuário                 | ✅ (self ou admin) |
| DELETE | `/user/delete/{id}`   | Remove usuário (self ou admin)            | ✅ |
| GET    | `/user/me`            | Retorna dados do usuário autenticado      | ✅ |

---

### 🛒 Produtos (`/product`)
| Método | Endpoint              | Descrição                                | Autenticação |
|--------|-----------------------|------------------------------------------|--------------|
| POST   | `/product/create`     | Cria novo produto                         | ✅ (admin) |
| DELETE | `/product/delete/{id}`| Remove produto                            | ✅ (admin) |
| PUT    | `/product/update/{id}`| Atualiza produto                          | ✅ (admin) |
| GET    | `/product/get/{id}`   | Busca produto por ID                      | ✅ (admin) |
| GET    | `/product/getall`     | Lista todos os produtos                   | ✅ (usuário autenticado) |

---

### 💳 Pagamentos (`/payments`)
| Método | Endpoint              | Descrição                                | Autenticação |
|--------|-----------------------|------------------------------------------|--------------|
| POST   | `/payments`           | Cria pagamento de produto via MercadoPago | ✅ |
| POST   | `/payments/webhook`   | Webhook do MercadoPago para atualizar status | ❌ (chamado pelo MercadoPago) |

---

### 📦 Compras (`/purchase`)
| Método | Endpoint              | Descrição                                | Autenticação |
|--------|-----------------------|------------------------------------------|--------------|
| POST   | `/purchase/create`    | Cria nova compra                         | ✅ (admin) |
| GET    | `/purchase/me`        | Lista compras do usuário autenticado      | ✅ |
| GET    | `/purchase/getall`    | Lista todas as compras                    | ✅ (admin) |
| GET    | `/purchase/get/{id}`  | Busca compra por ID                       | ✅ (admin) |
| PUT    | `/purchase/update/{id}`| Atualiza compra                          | ✅ (admin) |
| DELETE | `/purchase/delete/{id}`| Remove compra                            | ✅ (admin) |

---

## 🔒 Segurança
- Autenticação via **JWT** no header `Authorization: Bearer <token>`
- CSRF desabilitado (não necessário com JWT)
- Permissões baseadas em **roles** (`ROLE_USER`, `ROLE_ADMIN`)

---

## 📜 Swagger
A documentação da API estará disponível em:
