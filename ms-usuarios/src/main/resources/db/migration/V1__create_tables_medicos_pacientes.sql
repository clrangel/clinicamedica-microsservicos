-- ================================
-- Migration V1: Criar tabelas iniciais
-- ================================

-- Tabela medicos
CREATE TABLE medicos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    crm VARCHAR(50) NOT NULL,
    especialidade VARCHAR(50),
    logradouro VARCHAR(255),
    bairro VARCHAR(100),
    cep VARCHAR(20),
    numero VARCHAR(20),
    complemento VARCHAR(50),
    cidade VARCHAR(100),
    uf VARCHAR(2)
);

-- Tabela pacientes
CREATE TABLE pacientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(50) NOT NULL,
    logradouro VARCHAR(255),
    bairro VARCHAR(100),
    cep VARCHAR(20),
    numero VARCHAR(20),
    complemento VARCHAR(50),
    cidade VARCHAR(100),
    uf VARCHAR(2)
);
