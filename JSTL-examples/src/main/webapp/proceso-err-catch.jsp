<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultado</title>
</head>
<body>

<c:catch var="problema">
	<fmt:parseNumber value="${param.dividendo}" type="number" var="dividendo"/>
	<fmt:parseNumber value="${param.divisor}" type="number" var="divisor"/>	
	Resultado de la division:<c:out value="${dividendo/divisor}"/>
</c:catch>

<c:if test="${not empty problema}">
 <b>No se ha podido realizar la division</b> <br />
	<details>
 		<summary>Mostrar más</summary>
 		<c:out escapeXml="false" value="${problema}"/>
	</details>
</c:if>

</body>
</html>
