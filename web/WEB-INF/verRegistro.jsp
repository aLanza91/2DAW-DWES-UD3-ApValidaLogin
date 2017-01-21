<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="validalogin.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ver Registro</title>
</head>
<body>
	<% BeanUsuario usuario = (BeanUsuario) request.getAttribute("usuario"); %>
	<p>Se ha registrado correctamente.</p>
	<p>Login del usuario: <%=usuario.getLogin() %></p>
	<p>Nombre del usuario: <%=usuario.getNombre() %></p>
	<form action="index.html" method="get">
	<input type="submit" value="volver">
	</form>
</body>
</html>