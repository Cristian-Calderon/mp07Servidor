<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Envío</title>
<meta charset="UTF-8">
<link href="estilos.css" rel="stylesheet" type="text/css" />
</head>
<!--captura de datos e inserción en el Javabean-->
<jsp:useBean id="mensa" scope="request"
	class="practica4.javabeans.Mensaje" />
<jsp:setProperty name="mensa" property="*" />
<%
if (request.getParameter("texto") != null) {
%>
<jsp:forward page="grabar.go" />
<%}%>
<body>
	<div class="content">
		<h1>Generación de mensajes</h1>
		<form method="post">
			<br />
			<br /> <b>Datos del mensaje</b><br />
			<br />
			<hr />
			<table>
				<tr>
					<td>Destinatario:</td>
					<td><input name="destino" size="40"></td>
				</tr>
				<tr>
					<td>Remitente:</td>
					<td><input name="remite" size="40"></td>
				</tr>
				<tr>
					<td>Mensaje:</td>
					<td><textarea name="texto"></textarea></td>
				</tr>
			</table>
			<hr />
			<br /> <input type="submit" name="Submit" value="Enviar"> <input
				type="reset" value="Reset">
		</form>
	</div>
</body>
</html>