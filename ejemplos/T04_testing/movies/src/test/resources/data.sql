-- data.sql

-- Creación de la tabla movies
CREATE TABLE IF NOT EXISTS movies (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255),
                                      year INT,
                                      runTime INT
);

-- Inserción de datos
INSERT INTO movies (title, year, runTime) VALUES
                                              ('Cadena perpetua', 1994, 142),
                                              ('El padrino', 1972, 175),
                                              ('Pulp Fiction', 1994, 154);