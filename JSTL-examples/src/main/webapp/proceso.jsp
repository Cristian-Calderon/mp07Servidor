<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="jsp.titulo" /></title>
</head>
<body>
<fmt:message key="jsp.mensaje1" />
<fmt:formatNumber value="${param.txtNumero}" type="currency"/>
<br/>
<fmt:message key="jsp.mensaje2" />
<fmt:parseDate value="${param.txtFecha}" type="date" pattern="dd/MM/yyyy" var="fechaConvertida" />
<fmt:formatDate value="${fechaConvertida}" pattern="EEEE, dd MMMM yyyy" type="date" /> 
<p><a href="test-fmt.jsp"><fmt:message key="global.inicio" /></a>
</body>
</html>


