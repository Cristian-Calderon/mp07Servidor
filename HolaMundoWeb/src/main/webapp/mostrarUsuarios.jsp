<%@ page language="java"%> 
<%@	page import="logica.Usuario"%>
<%@	page import="java.util.List"%>
<%@	page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mostrar Usuarios</title>
</head>
<body>

	<h1>Lista de usuarios registrados</h1>
	<%
		List<Usuario> listaUsuarios = (List)request.getSession().getAttribute("listaUsuarios");
	int cont = 1;
	for(Usuario usu: listaUsuarios){
	%>
	
	<p><b>Usuario N <%=cont%></b></p>
	<p>Dni: <%=usu.getDni()%></p>
	<p>Nombre: <%=usu.getNombre()%></p>
	<p>Apellido: <%=usu.getApellido()%></p>
	<p>Telefono: <%=usu.getTelefono()%></p>
	<p>=====================================</p>
	<% cont++; } %>
	
</body>
</html>



<%-- 
	Para comentar son => ctrol + shift + /
	Sugerencias Ctrl +  space

 	<% String nombre = "Cristian";%>
	<h1><%= nombre %></h1> 
	<h2>Alt + shift + Y = Toggle Word Wrap on Eclipse</h2>
	<h2>Para identar el codigo: Ctrl + Shift + F</h2>
 --%>