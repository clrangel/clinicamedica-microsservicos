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
- MapStruct (mapeamento automático entre DTOs e entidades)
---
- Spring Cloud Netflix Eureka Server — implementação do Service Registry
- Spring Cloud Netflix Eureka Client — registro e descoberta automática dos microsserviços
- Spring Cloud Discovery — integração nativa com o ecossistema Spring

---

## 🧩 Microsserviços

### 1. ms-usuarios
Gerencia as informações de **médicos, pacientes, funcionários (no futuro) e endereços**, incluindo validação e persistência dos dados no banco PostgreSQL.

#### Funcionalidades atuais:
- Cadastro de Médico com informações pessoais e endereço embutido
- Cadastro de Paciente com informações pessoais e endereço embutido
- Conversão automática entre DTOs e entidades utilizando MapStruct
- Validações com Jakarta Validation
- Controle de versão do schema com Flyway
- Registro automático no Eureka Server como cliente de descoberta de serviços

### 2. ms-consultas
Responsável pelo **agendamento de consultas**, integração com o microsserviço de usuários e envio de notificações (e-mails de confirmação de consulta).

Funcionalidades atuais:

- Cadastro de consultas médicas, armazenando pacienteId, medicoId, data e horário

- Controle de versão do schema com Flyway

- Registro automático no Eureka Server como cliente de descoberta de serviços

---

### 3. service-registry

Microsserviço responsável pelo Service Discovery da aplicação.
Implementa o padrão Service Registry utilizando Spring Cloud Netflix Eureka Server.

Funcionalidades:

Atua como servidor central de registro (Eureka Server)

Permite que os microsserviços (ms-usuarios, ms-consultas) se autoregistrem e descubram dinamicamente uns aos outros

Facilita a comunicação entre microsserviços sem depender de URLs fixas

Interface acessível via navegador em http://localhost:8081, onde todos os serviços registrados ficam visíveis

---

## 🌩️ Ecossistema Spring Cloud Discovery

Este projeto utiliza o ecossistema Spring Cloud Discovery, que oferece suporte a registro e descoberta automática de microsserviços.

O Eureka Server atua como o registro central (Service Registry).

Os Eureka Clients (ms-usuarios e ms-consultas) se registram automaticamente no servidor e consultam outros serviços quando necessário.

Essa configuração garante escalabilidade, resiliência e baixo acoplamento entre os microsserviços, permitindo que a comunicação ocorra sem a necessidade de configurações manuais de endereços.

---
## 🗄️ Configuração do Banco de Dados (PostgreSQL)

O projeto utiliza PostgreSQL.  
Antes de rodar os microsserviços, crie os bancos de dados correspondentes:

### Para o microsserviço de usuários:
```bash
CREATE DATABASE ms_usuariosdb;
```
### Para o microsserviço de consultas:
```bash
### CREATE DATABASE ms_consultasdb;
```

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
Após clonar, acesse o microsserviço desejado (ms-usuarios, ms-consultas, etc.) e execute o projeto pela sua IDE ou terminal.

Certifique-se de:

Ter o PostgreSQL em execução e com os bancos criados conforme instruído acima.

Atualizar o arquivo application.properties de cada microsserviço com seu usuário e senha do PostgreSQL.

Atualizar o arquivo application.properties de cada microsserviço com o nome correto de cada banco de dados.

Ter o Java 21 configurado no ambiente.

## 💬 Sobre o Projeto
O clinicamedica-microsservicos é um projeto pessoal desenvolvido para estudo e prática de arquitetura de microsserviços, integração com bancos de dados, mensageria (RabbitMQ) e Docker.

Ele será evoluído gradualmente, com foco em qualidade, organização e aplicabilidade em ambientes reais.

## 🧠 Objetivo
Criação de microsserviços independentes em Java/Spring Boot.

Integração entre serviços.

Controle de versão de banco com Flyway.

Preparação para deploy em contêineres Docker.

Boas práticas de código e arquitetura.

