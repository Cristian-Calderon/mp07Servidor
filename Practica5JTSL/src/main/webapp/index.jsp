<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ejemplo JSTL</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<h1>Gestion de estudiantes</h1>
	<p>Consultas</p>

	<form action="" method="">
		<input type="text" id="sentencia" name="sentencia" placeholder="SELECT * FROM alumnos" required>
		<input type="submit" value="Ejecutar">
		
		<!-- Se añade checkboox para seleccionar si se usa la JSTL o no -->
		<h2>Opciones</h2>
		<label>Técnica JSTL en resultados consulta:</label>
		<input type="radio"  id="jstl-si" value="true" name="jstl" checked>
		<label for="jstl-si">Sí</label>
		<input type="radio" id="jstl-no" value="false" name="jstl">
		<label for="jstl-no"">No</label>
		<!-- FIN -->
	</form> 

</body>
</html>
