-- Tabela de usuários
DROP TABLE IF EXISTS usuarios CASCADE;
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    papel VARCHAR(50) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_usuarios_email ON usuarios(email);

-- Tabela de equipes
DROP TABLE IF EXISTS equipes CASCADE;
CREATE TABLE equipes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    capacidade_diaria INTEGER,
    em_uso DOUBLE PRECISION,
    cpus_em_processamento INTEGER,
    tempo_por_unidade INTEGER,
    status VARCHAR(50),
    tendencia VARCHAR(50),
    proxima_disponibilidade TIMESTAMP,
    ultima_atualizacao TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Tabela de membros de equipe
DROP TABLE IF EXISTS membro_equipe CASCADE;
CREATE TABLE membro_equipe (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    funcao VARCHAR(100),
    equipe_id BIGINT REFERENCES equipes(id),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Tabela de ferramentas das equipes
DROP TABLE IF EXISTS equipes_ferramentas CASCADE;
CREATE TABLE equipes_ferramentas (
    equipe_id BIGINT REFERENCES equipes(id),
    ferramentas VARCHAR(255)
);

-- Tabela de procedimentos das equipes
DROP TABLE IF EXISTS equipes_procedimentos CASCADE;
CREATE TABLE equipes_procedimentos (
    equipe_id BIGINT REFERENCES equipes(id),
    procedimentos VARCHAR(255)
); 