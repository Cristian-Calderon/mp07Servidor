<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Información de Sesión</title>
</head>
<body>
    <h2>Información de la Sesión</h2>
    <table border="1">
        <tr><td>Identificador</td><td><%= session.getId() %></td></tr>
        <tr><td>Fecha Creación</td><td><%= new java.util.Date(session.getCreationTime()) %></td></tr>
        <tr><td>Último Acceso</td><td><%= new java.util.Date(session.getLastAccessedTime()) %></td></tr>
        <tr><td>Número de Accesos</td><td><%= session.getAttribute("contadorAccesos") %></td></tr>
        <tr><td>Usuario</td><td><%= session.getAttribute("usuario") != null ? session.getAttribute("usuario") : "No registrado" %></td></tr>
    </table>
</body>
</html>
