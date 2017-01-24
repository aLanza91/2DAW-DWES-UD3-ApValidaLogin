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
	<jsp:useBean id="usuario" scope="request" class="validalogin.beans.BeanUsuario" />
	<%usuario = (BeanUsuario) request.getAttribute("usuario"); %>
	<p>Nombre del usuario: <jsp:getProperty name="usuario" property="nombre" /></p>
	<form action="index.html" method="get">
	<input type="submit" value="volver">
	</form>
</body>
</html>