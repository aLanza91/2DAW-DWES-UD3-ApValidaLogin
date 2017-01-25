<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Introduzca datos</title>
<style>
	div{
	position: absolute;
	left: 40%;
	}
</style>
</head>
<body>
<% 	String nombre="";
	if (request.getParameter("nombre") != null) {
		nombre = request.getParameter("nombre");
	}
 %>
<div>
<fieldset>
<form action="/validalogin/validar" method="post">
<label for="usuario">Usuario:</label><input id="usuario" type="text" name="usuario" maxlength="8" placeholder="Máximo 8" required="required"><br>
<label for="clave">Clave:</label><input id="clave" type="password" name="clave" maxlength="8" placeholder="Máximo 8" required="required" autocomplete="off"><br>
<input type="submit" value="Login">
</form>
<hr>
<form action="/validalogin/registrar" method="post">
<label for="nombre">Nombre:</label><input id="nombre" type="text" name="nombre" maxlength="128" placeholder="Máximo 128" required="required" value="<%=nombre%>"><br>
<label for="usuario2">Usuario:</label><input id="usuario2" type="text" name="usuario" maxlength="8" placeholder="Máximo 8" required="required"><br>
<label for="clave">Clave:</label><input id="clave" type="password" name="clave" maxlength="8" placeholder="Máximo 8" required="required" autocomplete="off"><br>
<input type="submit" value="Registrar">
</form>
</fieldset>
</div>
</body>
</html>