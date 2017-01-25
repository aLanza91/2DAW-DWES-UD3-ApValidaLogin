# 2DAW-DWES-UD3-ApValidaLogin



<datasource jta="true" jndi-name="java:jboss/datasources/dsbdvalidalogin" pool-name="dsbdvalidalogin" enabled="true" use-java-context="true" use-ccm="true">
                    <connection-url>jdbc:mysql://localhost:3306/logins</connection-url>
                    <driver>mysql5</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>mysql</password>
                    </security>
</datasource>

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
 ('ana','clave','ana gracia'),
 ('antonio','1234','antonio sanchez');