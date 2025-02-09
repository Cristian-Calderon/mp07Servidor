<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.ServletContext" %>
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

        <%
            // Obtener el contexto del servlet para acceder a los atributos globales
            ServletContext contexto = getServletContext();
            Integer usuariosConectados = (Integer) contexto.getAttribute("usuariosConectados");
            Integer usuariosValidados = (Integer) contexto.getAttribute("usuariosValidados");

            // Evitar valores nulos
            if (usuariosConectados == null) usuariosConectados = 0;
            if (usuariosValidados == null) usuariosValidados = 0;
        %>

        <tr><td>Usuarios Conectados</td><td><%= usuariosConectados %></td></tr>
        <tr><td>Usuarios Validados</td><td><%= usuariosValidados %></td></tr>
    </table>
</body>
</html>
