package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.Conexion;

/**
 * Servlet implementation class Svmain
 */
@WebServlet("/Svmain")
public class Svmain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Svmain() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtener la sentencia SQL desde el formulario
        String sentenciaSQL = request.getParameter("sentencesql");
        
     // Configurar la conexión a la base de datos
        Conexion.setURL("jdbc:mysql://localhost:3306/pruebas?user=daw2&password=secreto");
        

        // Obtener la conexión
        Connection con = Conexion.getConexion();
        if (con != null) {
            try {
                // Crear el statement para ejecutar la sentencia SQL
                Statement stmt = con.createStatement();
                
                // Ejecutar la sentencia SQL
                stmt.executeUpdate(sentenciaSQL);

                // Enviar una respuesta al cliente
                response.getWriter().println("Sentencia SQL ejecutada exitosamente: " + sentenciaSQL);

            } catch (SQLException e) {
                // Manejo de errores de SQL
                response.getWriter().println("Error al ejecutar la sentencia SQL: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Desconectar de la base de datos
                Conexion.desconecta();
            }
        } else {
            response.getWriter().println("No se pudo establecer la conexionn a la base de datos.");
        }
    }
}
