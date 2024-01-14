-- schema.sql

-- Creación de la tabla movies
CREATE TABLE IF NOT EXISTS movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    "year" INT, -- Utilizar comillas dobles para evitar conflicto con la palabra reservada
    runtime INT
);