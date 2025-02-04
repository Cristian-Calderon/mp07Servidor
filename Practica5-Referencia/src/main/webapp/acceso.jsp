<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
    <h2>Validacion de usuario</h2>
    <form action="Controlador" method="post">
    <p>La operacion solicitada requiere validacion <br> Por favor, introduzca sus credenciales</p>
        <input type="hidden" name="operacion" value="validar">
        Usuario: <input type="text" name="txtUsuario"><br>
        Contraseña: <input type="password" name="txtContrasenya"><br>
        <input type="submit" value="Ingresar">
    </form>
</body>
</html>
