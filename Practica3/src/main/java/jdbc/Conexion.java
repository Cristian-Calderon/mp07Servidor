package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/dbalumnos"; // Cambia por el nombre de tu base de datos.
    private static final String USER = "daw2"; // Usuario de la BD.
    private static final String PASSWORD = "secreto"; // Contraseña del usuario.

    public static Connection getConexion() throws SQLException {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error al cargar el driver JDBC.");
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}