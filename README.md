# 🏥 clinicamedica-microsservicos

Este projeto é uma aplicação **backend distribuída**, desenvolvida em **Java 21** com **Spring Boot 3**, baseada em **arquitetura de microsserviços**.  
O objetivo é demonstrar boas práticas de desenvolvimento, integração entre serviços e uso de tecnologias modernas do ecossistema Spring.

## 📝 Como Funciona a Aplicação

Esta é uma aplicação para **agendamento de consultas médicas**.

O fluxo principal é:

1. **Cadastro de Médicos**: É possível registrar médicos com suas informações e especialidades.
2. **Cadastro de Pacientes**: Pacientes podem ser cadastrados com seus dados pessoais e endereço.
3. **Agendamento de Consultas**: Um paciente pode marcar uma consulta com um médico disponível.
4. **Confirmação por E-mail**: Após a consulta ser criada, um **e-mail de confirmação** é enviado automaticamente para o paciente.

Essa abordagem demonstra a integração entre microsserviços, persistência de dados no PostgreSQL e envio de notificações via RabbitMQ.

---

## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Flyway Migration
- Validation API
- Lombok
- Spring DevTools

---

## 🧩 Microsserviços

### 1. ms-usuarios
Gerencia as informações de **médicos, pacientes, funcionários (no futuro) e endereços**, incluindo validação e persistência dos dados no banco PostgreSQL.

### 2. ms-consultas
Responsável pelo **agendamento de consultas**, integração com o microsserviço de usuários e envio de notificações (e-mails de confirmação de consulta).

---

## 🗄️ Configuração do Banco de Dados

O projeto utiliza PostgreSQL.  
Antes de rodar o microsserviço `ms-usuarios`, crie o banco de dados:

CREATE DATABASE ms_usuariosdb;

## 🗄️ Configurações no `application.properties`

`spring.application.name=ms-usuarios`

`spring.datasource.url=jdbc:postgresql://localhost:5432/ms_usuariosdb`

`spring.datasource.username=seu_usuario`

`spring.datasource.password=sua_senha`

`# spring.jpa.hibernate.ddl-auto=update` → Permite ao Hibernate criar/alterar automaticamente o schema do banco, mas **não deve ser usada com Flyway** para evitar conflitos

`spring.jpa.show-sql=true` → Exibe o SQL gerado no console

Observação: Para executar o microsserviço, é necessário configurar o application.properties com seu usuário e senha do PostgreSQL e executar o projeto em sua IDE ou terminal. Isso é esperado para quem for analisar o projeto.


## 🚀 Como Executar o Projeto

### Passo 1: Clonar o repositório
```bash
git clone https://github.com/seuusuario/clinicamedica-microsservicos.git
```
### Passo 2: Acessar o microsserviço
```bash
cd clinicamedica-microsservicos/ms-usuarios
```
## 💬 Sobre o Projeto
O clinicamedica-microsservicos é um projeto pessoal desenvolvido para estudo e prática de arquitetura de microsserviços, integração com bancos de dados, mensageria (RabbitMQ) e Docker.

Ele será evoluído gradualmente, com foco em qualidade, organização e aplicabilidade em ambientes reais.

## 🧠 Objetivo
Criação de microsserviços independentes em Java/Spring Boot

Integração entre serviços

Controle de versão de banco com Flyway

Preparação para deploy em contêineres Docker

Boas práticas de código e arquitetura

