<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario de Prueba</title>
</head>

<body>
	<h1>Datos del usuario</h1>

	<form action="./SvUsuario" method="POST">
		<p>
			<label>Dni: </label> <input type="text" name="dni">
		</p>
		<p>
			<label>Nombre: </label> <input type="text" name="nombre">
		</p>
		<p>
			<label>Apellido: </label> <input type="text" name="apellido">
		</p>
		<p>
			<label>Telefono: </label> <input type="text" name="telefono">
		</p>
		<button type="submit">Registrarse</button>
	</form>


	<%-- 
	Para comentar son => ctrol + shift + /
	Sugerencias Ctrl +  space

 	<% String nombre = "Cristian";%>
	<h1><%= nombre %></h1> 
	<h2>Alt + shift + Y = Toggle Word Wrap on Eclipse</h2>
	<h2>Para identar el codigo: Ctrl + Shift + F</h2>
 --%>

	<h1>Ver lista de usuarios</h1>
	<p>Para ver los datos de los usuarios cargados haga click en el
		siguiente boton</p>
	<form action="./SvUsuario" method="GET">
	<button type="submit">Mostrar Usuarios</button>
	</form>

</body>
</html>

