-- Crear tabla de usuarios
CREATE TABLE IF NOT EXISTS USERS (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,
    last_login TIMESTAMP NOT NULL,
    token VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL
);

-- Crear tabla de teléfonos
CREATE TABLE IF NOT EXISTS PHONES (
    id UUID PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    city_code VARCHAR(10) NOT NULL,
    country_code VARCHAR(10) NOT NULL,
    user_id UUID,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES USERS(id)
);

-- Insertar datos de prueba (opcional)
INSERT INTO USERS (id, name, email, password, created, modified, last_login, token, active) VALUES
('123e4567-e89b-12d3-a456-426614174000', 'Test User', 'test@user.com', 'Test@1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'sample_token', TRUE);

INSERT INTO PHONES (id, number, city_code, country_code, user_id) VALUES
('123e4567-e89b-12d3-a456-426614174003', '1234567', '1', '57', '123e4567-e89b-12d3-a456-426614174000');