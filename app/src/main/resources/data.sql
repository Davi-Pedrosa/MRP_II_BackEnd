-- Senha: admin123
INSERT INTO usuarios (id, nome, email, senha, papel, created_at, updated_at)
SELECT 
    gen_random_uuid(), 
    'Administrador', 
    'admin@mrp2.com', 
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4u', 
    'ADMIN',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
WHERE NOT EXISTS (
    SELECT 1 FROM usuarios WHERE email = 'admin@mrp2.com'
);

-- Senha: admin1234 (hash gerado com BCrypt)
INSERT INTO usuarios (id, nome, email, senha, papel, created_at, updated_at)
VALUES (
    gen_random_uuid(),
    'Administrador',
    'admin@email.com',
    '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG',
    'ADMIN',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
) ON CONFLICT (email) DO NOTHING;

-- Senha: funcionario1234 (hash gerado com BCrypt)
INSERT INTO usuarios (id, nome, email, senha, papel, created_at, updated_at)
VALUES (
    gen_random_uuid(),
    'Funcionário',
    'funcionario@email.com',
    '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG',
    'FUNCIONARIO',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
) ON CONFLICT (email) DO NOTHING; 