package practica4.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection getConexion() throws SQLException {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión con la base de datos
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmensajes", "usuario", "contraseña");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error al conectar con la base de datos", e);
        }
    }
}
