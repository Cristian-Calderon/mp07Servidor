<%@ taglib prefix="sql" uri="jakarta.tags.sql"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultado</title>
</head>
<body>
<h1>Resultado de la operacion</h1>
<c:if test="${!empty param.operacion}">

    <b><i><c:out value="${param.operacion}" /></i></b>   
  
    <c:choose>
          <c:when test="${param.operacion eq 'consulta'}">
              <sql:query var="alumnos" sql="${param['txtQuery']}" />
              <table>
              <c:forEach var="columna" items="${alumnos.columnNames}">
                   <th><c:out value="${fn:toUpperCase(columna)}" /></th>
               </c:forEach>
               <c:forEach var="alumno" items="${alumnos.rows}">
                  <tr>
                    <td><c:out value="${alumno.id}" /></td>
                    <td><c:out value="${alumno.curso}" /></td>
                   <td><c:out value="${alumno.nombre}" /></td>
                  </tr>
              </c:forEach>
              </table>
              <br />
           </c:when>
           <c:when test="${param.operacion eq 'actualizacion'}">
        <sql:update var="filasAfectadas" sql="${param['txtUpdate']}" />
        <c:choose>
           <c:when test="${filasAfectadas>0}">
              Filas afectadas:<c:out value="${filasAfectadas}" />
           </c:when>
           <c:otherwise>
              <c:out value="La actualizacion no ha devuelto resultados" /><br />
	</c:otherwise>
        </c:choose>  <br />
     </c:when>
     <c:otherwise>
        <c:out value=": operacion no soportada" /><br />
     </c:otherwise>
  </c:choose>
</c:if>
<p><a href="index-sql.html">Regresar</a>
</body>
</html>