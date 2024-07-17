--
-- Database: `management_users`
--

CREATE DATABASE `management_users`;
USE `management_users`;

-- Crear tabla de usuarios
CREATE TABLE IF NOT EXISTS USER (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,
    last_login TIMESTAMP NOT NULL,
    token VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL
);

-- Crear tabla de teléfonos
CREATE TABLE IF NOT EXISTS PHONE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    citycode VARCHAR(10) NOT NULL,
    countrycode VARCHAR(10) NOT NULL,
    user_id UUID,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES USER(id)
);

-- Insertar datos de prueba (opcional)
INSERT INTO USER (id, name, email, password, created, modified, last_login, token, is_active) VALUES 
('123e4567-e89b-12d3-a456-426614174000', 'Test User', 'test@user.com', 'Test@1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'sample_token', TRUE);

INSERT INTO PHONE (number, citycode, countrycode, user_id) VALUES
('1234567', '1', '57', '123e4567-e89b-12d3-a456-426614174000');