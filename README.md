# 🚀 Demo Spring Boot API

API REST desenvolvida em **Spring Boot** com autenticação via **JWT**, documentação com **Swagger** e suporte a **Docker** para execução local e deploy no Railway.

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

---

## ⚙️ Funcionalidades
- Registro e autenticação de usuários (`/auth/register`, `/auth/login`)
- Proteção de rotas com JWT
- CRUD de usuários com controle de permissões
- Endpoint `/user/me` para retornar dados do usuário autenticado
- Documentação interativa via Swagger UI

---

## 📖 Endpoints principais

| Método | Endpoint              | Descrição                          | Autenticação |
|--------|-----------------------|------------------------------------|--------------|
| POST   | `/auth/register`      | Cadastro de novo usuário           | ❌           |
| POST   | `/auth/login`         | Login e geração de token JWT       | ❌           |
| GET    | `/user/me`            | Retorna dados do usuário logado    | ✅           |
| PUT    | `/user/update/{id}`   | Atualiza dados do usuário          | ✅ (próprio ou admin) |
| DELETE | `/user/delete/{id}`   | Remove usuário                     | ✅ (próprio ou admin) |

---

## 🛠️ Como rodar localmente

### Via Maven
```bash
./mvnw spring-boot:run
