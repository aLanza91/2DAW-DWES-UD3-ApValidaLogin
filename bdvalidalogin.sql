DROP DATABASE IF EXISTS logins;
CREATE DATABASE logins;
USE logins;
CREATE TABLE login (
  login VARCHAR(8),
  clave VARCHAR(8) NOT NULL,
  nombre VARCHAR(128) NOT NULL,
  PRIMARY KEY (login)
);


INSERT INTO login (login,clave,nombre) VALUES
('ana','clave','ana gracia'),
('antonio','1234','antonio sanchez');
