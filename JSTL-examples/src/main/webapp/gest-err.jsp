<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Problemas</title>
</head>
<body>
 <b>No se ha podido realizar la division</b> <br />
	<details>
 		<summary>Mostrar más</summary>
 		<c:out escapeXml="false" value="${pageContext.exception.message}"/>
	</details> 
</body>
</html>