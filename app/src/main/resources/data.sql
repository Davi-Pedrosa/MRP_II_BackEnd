-- Senha: admin123
INSERT INTO usuarios (nome, email, senha, papel, created_at, updated_at)
SELECT 'Administrador', 'admin@mrp2.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4u', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = 'admin@mrp2.com');

-- Senha: admin1234
INSERT INTO usuarios (nome, email, senha, papel, created_at, updated_at)
SELECT 'Administrador', 'admin@email.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = 'admin@email.com');

-- Senha: funcionario1234
INSERT INTO usuarios (nome, email, senha, papel, created_at, updated_at)
SELECT 'Funcionário', 'funcionario@email.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'FUNCIONARIO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = 'funcionario@email.com');

-- Inserir dados de exemplo para equipes
INSERT INTO equipes (nome, tipo, capacidade_diaria, em_uso, cpus_em_processamento, tempo_por_unidade, status, tendencia, proxima_disponibilidade, ultima_atualizacao, created_at, updated_at)
VALUES 
('Equipe de Produção A', 'PRODUCTION', 100, 75.5, 8, 45, 'NORMAL', 'ESTAVEL', CURRENT_TIMESTAMP + INTERVAL '2 days', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Equipe de Manutenção', 'MAINTENANCE', 80, 60.0, 4, 30, 'NORMAL', 'SUBINDO', CURRENT_TIMESTAMP + INTERVAL '1 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserir dados de exemplo para membros de equipe
INSERT INTO membro_equipe (nome, funcao, equipe_id, created_at, updated_at)
SELECT 'João Silva', 'Operador', e.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM equipes e
WHERE e.nome = 'Equipe de Produção A';

INSERT INTO membro_equipe (nome, funcao, equipe_id, created_at, updated_at)
SELECT 'Maria Santos', 'Técnica', e.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM equipes e
WHERE e.nome = 'Equipe de Manutenção'; 