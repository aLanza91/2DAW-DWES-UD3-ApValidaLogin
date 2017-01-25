<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="validalogin.beans.BeanError" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
<style>
	div{
	position: absolute;
	left: 30%;
	}
</style>
</head>
<body>
<div>
<%	
	if (request.getAttribute("error") != null) {
		BeanError error = (BeanError) request.getAttribute("error");
		out.println(error.toString());
		} else {
			String pagOrigenError = request.getParameter("pagOrigen");%>
			<p>Ha ocurrido un error en la página: <%=pagOrigenError%></p>
			<p>Descripción de la excepción: <%=exception.toString()%></p>		
		<%}%> 
<form action="index.jsp" method="get">
<input type="submit" value="volver">
<input type="hidden" name="nombre" value='<%=request.getAttribute("nombre") %>' />
</form>
</div>
</body>
</html>