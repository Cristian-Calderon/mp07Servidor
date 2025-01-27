<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br/><br/>
<c:out value="***En ejemplos2.jsp"/>
<br/>
<p><b>Ejemplo 3: requestScope; recuperarla de la request</b><br/>
<!-- Con param cogemos informacion del navegador -->
<c:out value="${param['nomUsuario']}"/><br/>
</body>
</html>