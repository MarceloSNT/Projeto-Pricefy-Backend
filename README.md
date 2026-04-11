🛒 Pricefy Backend

API REST desenvolvida para gerenciamento inteligente de listas de compras, permitindo ao usuário registrar produtos, mercados e preços para gerar listas com os menores valores disponíveis.

📌 Sobre o projeto

O Pricefy Backend é um sistema que facilita a criação de listas de compras com base nos menores preços cadastrados pelo próprio usuário.

A proposta é ajudar no controle de gastos e otimização de compras, centralizando informações de produtos, mercados e preços em uma única API.

🚀 Tecnologias utilizadas
Java 21
Spring Boot
Spring Security
JWT (JSON Web Token)
PostgreSQL
Spring Data JPA
Hibernate
Swagger (OpenAPI)
🔐 Autenticação e segurança

A API utiliza JWT para autenticação e autorização.

✔️ Funcionalidades:
Registro de usuário
Login autenticado
Proteção de rotas
Controle de acesso por usuário
📥 Uso do token:

O token deve ser enviado no header das requisições:

Authorization: Bearer SEU_TOKEN

🧱 Estrutura da aplicação

O projeto segue uma arquitetura em camadas:

src/
 ├── controller   # Endpoints da API
 ├── service      # Regras de negócio
 ├── repository   # Acesso ao banco de dados
 ├── model        # Entidades
 ├── dto          # Objetos de transferência de dados
 ├── security     # Configurações de autenticação (JWT)
 └── utils        # Métodos auxiliares
 
📦 Entidades principais

👤 User
Responsável pelo acesso ao sistema.

🛒 Product
Produtos cadastrados pelo usuário.

🏪 Market
Mercados associados ao usuário.

💰 Price
Permite identificar o menor preço entre diferentes mercados.

🎯 Objetivo principal

Permitir que o usuário:
Cadastre produtos e mercados
Registre preços
Compare valores
Gere listas de compras mais econômicas
📊 Documentação da API

A API conta com documentação interativa via Swagger:
http://localhost:8080/swagger-ui.html

👨‍💻 Autor
Marcelo dos Santos Machado
💼 Desenvolvedor Back-end
🔗 GitHub: https://github.com/MarceloSNT
