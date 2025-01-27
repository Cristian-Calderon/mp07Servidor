<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<c:if test="${param['lang']!=null}">
	<fmt:setLocale value="${param['lang']}" scope="session"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="formulario.titulo" /></title>
</head>
<body>
<h1><fmt:message key="formulario.cabecera" /></h1>
<form action="proceso.jsp" method="post">
<label id="txtNumero"><fmt:message key="formulario.mensaje1" /></label>
<input type="text" name="txtNumero" size="10" value="102525.78"/><br/>
<label id="txtNumero"><fmt:message key="formulario.mensaje2" /></label>
<input type="text" name="txtFecha" size="10" value="01/01/2025"/><br/>
<input type="submit" value='<fmt:message key="global.submit"/>'/>
</form>
<p><a href="?lang=en_US"><fmt:message key="global.idioma1" /></a>
<p><a href="?lang=es_ES"><fmt:message key="global.idioma2" /></a>
</body>
</html>