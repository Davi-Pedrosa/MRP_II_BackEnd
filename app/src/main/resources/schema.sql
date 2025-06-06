DROP TABLE IF EXISTS usuarios CASCADE;

CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    papel VARCHAR(50) NOT NULL
);

-- Índice para busca por email
CREATE INDEX IF NOT EXISTS idx_usuarios_email ON usuarios(email); 