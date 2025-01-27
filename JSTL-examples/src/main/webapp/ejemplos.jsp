<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Uso de la libreria JSTL</title>
</head>
<body>

<p><b>Ejemplo 1: Etiqueta 'set' sirve para crear una variable y opcionalmente darle un valor </b></p>
<c:set var="miDato" value="111"/>

El valor de mi dato es: ${miDato}
<br>


<p><b>Ejemplo 2 : poner una variable en la request es imprimirla</b></p>
<c:set var="nomUsuario" value="sin_nombre" scope="request"/>
nomUsuario: <c:out value="${nomUsuario}"/>

<jsp:include page="ejemplos2.jsp" />
<br/>
<c:out value="***En ejemplos1.jsp"/>


<p><b>Ejemplo 5: paramValues-Recibiendo y mostrando parámetros</b><br/>

<b>Un ejemplo iterando por todo el array de Strings. Resultado:</b><br/>

<c:forEach var="varTmp" items="${paramValues.opciones}">
	<c:out value="${varTmp}"/><br/>
</c:forEach>


<b>Ahora un ejemplo seleccionando los dos primeros elementos de la lista con dos sintaxis diferentes...</b><br/>
<b>Primera sintaxis. Resultado:</b><br/>
<c:out value="${paramValues.opciones[0]}"/><br/>
<c:out value="${paramValues.opciones[1]}"/><br/>

<!-- Otra manera -->
<b>Segunda sintaxis. Resultado:</b><br/>

<c:out value="${paramValues['opciones'][0]}"/><br/>
<c:out value="${paramValues['opciones'][1]}"/><br/>

<p><b>Ejemplo 6: Encabezados</b><br/>
      <c:out value="${header['user-agent']}"/><br/>



<jsp:useBean id="miLibro" class="beans.LibroBean"/>
<%
miLibro.setTitulo("Saliendo de la crisis");
miLibro.setAutor("Zapatero",0); // además del autor especificamos la posición
miLibro.setAutor("Rajoy",1); // además del autor especificamos la posición

out.print("<br/><b>Datos del libro:</b><br/>");

out.print("Titulo : "+miLibro.getTitulo()+"<br/>");
out.print("Autor 1: "+miLibro.getAutor(0)+"<br/>");
out.print("Autor 2: "+miLibro.getAutor(1)+"<br/>");
%>


</body>
</html>