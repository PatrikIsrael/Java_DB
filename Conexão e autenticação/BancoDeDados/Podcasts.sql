CREATE DATABASE podcasts;
USE podcasts;
CREATE TABLE podcast (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nome_episodio VARCHAR(255) NOT NULL,
    numero_episodio INT,
    duracao VARCHAR(50),
    url_repositorio VARCHAR(255)
);
select * from podcast;

USE podcasts;