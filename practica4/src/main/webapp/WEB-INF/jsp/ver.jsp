<%@ page import="practica4.javabeans.*,java.util.*"%>
<html>
<head>
<title>Ver</title>
<meta charset="UTF-8">
<link href="estilos.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="content">
        <h1>Mensajes para <%= request.getParameter("nombre") %></h1>
        <table>
            <tr>
                <th>Remitente</th>
                <th>Mensaje</th>
            </tr>
            <%
            ArrayList<Mensaje> mensajes = (ArrayList<Mensaje>) request.getAttribute("mensajes");
            if (mensajes != null && !mensajes.isEmpty()) {
                for (Mensaje m : mensajes) {
            %>
            <tr>
                <td><%= m.getRemite() %></td>
                <td><%= m.getTexto() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr><td colspan="2">No tienes mensajes.</td></tr>
            <%
            }
            %>
        </table>
        <br />
        <a href="index.html">Inicio</a>
    </div>
</body>
</html>
