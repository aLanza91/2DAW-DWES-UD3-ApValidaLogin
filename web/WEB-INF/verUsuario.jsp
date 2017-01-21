<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="validalogin.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ver usuario</title>
</head>
<body>
	<!--Por qué no funciona ?-->
	<jsp:useBean id="usuario2" scope="page" class="validalogin.beans.BeanUsuario" />
	<%usuario2 = (BeanUsuario) request.getAttribute("usuario"); %>
	<p>Nombre del usuario: <jsp:getProperty name="usuario2" property="nombre" /></p>
	
	<% BeanUsuario usuario = (BeanUsuario) request.getAttribute("usuario"); %>
	<p>Nombre del usuario: <%=usuario.getNombre() %></p>
	<form action="index.html" method="get">
	<input type="submit" value="volver">
	</form>
</body>
</html>