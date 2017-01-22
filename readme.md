Ejercicio: Servlet para validar un login

Partiendo de un formulario que recoge el login y clave de un usuario, diseñar un servlet que recibiendo ambos datos los coteje en una base de datos, mostrando una página con los datos introducidos y el nombre del usuario en caso de validación correcta, o devolviendo al usuario al formulario añadiendo en éste, un mensaje con la causa del error.

Base de datos: logins

Tabla: login

Campos de la tabla login:

login - VARCHAR(8)

clave - VARCHAR(8)

nombre - VARCHAR(128)

Nombre del servlet: ValidaLogin

DataSource:

                <datasource jta="true" jndi-name="java:jboss/datasources/dsbdvalidalogin" pool-name="dsbdvalidalogin" enabled="true" use-java-context="true" use-ccm="true">
                    <connection-url>jdbc:mysql://localhost:3306/logins</connection-url>
                    <driver>mysql5</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>mysql</password>
                    </security>
                </datasource>