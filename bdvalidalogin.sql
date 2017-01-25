DROP DATABASE IF EXISTS logins;
CREATE DATABASE logins;
USE logins;
CREATE TABLE login (
  usuario VARCHAR(8),
  clave VARCHAR(8) NOT NULL,
  nombre VARCHAR(128) NOT NULL,
  PRIMARY KEY (usuario)
);


INSERT INTO login (usuario,clave,nombre) VALUES
('bubu','1234','bubu perez'),
('antonio','1234','antonio lanza');
