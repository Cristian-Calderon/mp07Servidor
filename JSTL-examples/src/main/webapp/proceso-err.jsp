<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- <%@ page errorPage = "gest-err.jsp"%>
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Resultado</title>
</head>
<body>

	<%
	response.sendError(500);
	%>
	
<fmt:parseNumber value="${param.dividendo}" type="number" var="dividendo"/>
<fmt:parseNumber value="${param.divisor}" type="number" var="divisor"/>
Resultado de la division:<c:out value="${dividendo/divisor}"/>

</body>
</html>