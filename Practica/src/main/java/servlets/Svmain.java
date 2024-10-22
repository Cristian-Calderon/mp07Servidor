package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.Conexion;

/**
 * Servlet implementation class Svmain
 */
@WebServlet("./Svmain")
public class Svmain extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Svmain() {
        super();
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

                // Verificamos si es una consulta de tipo SELECT
                if (sentenciaSQL.trim().toUpperCase().startsWith("SELECT")) {
                    // Ejecutar la sentencia SQL de consulta
                    ResultSet rs = stmt.executeQuery(sentenciaSQL);

                    // Procesar el resultado y enviarlo al cliente
                    StringBuilder resultado = new StringBuilder();
                    resultado.append("<table border='1'>");

                    // Obtener información sobre las columnas
                    int columnCount = rs.getMetaData().getColumnCount();

                    // Imprimir encabezados de columna
                    resultado.append("<tr>");
                    for (int i = 1; i <= columnCount; i++) {
                        resultado.append("<th>").append(rs.getMetaData().getColumnName(i)).append("</th>");
                    }
                    resultado.append("</tr>");

                    // Imprimir las filas
                    while (rs.next()) {
                        resultado.append("<tr>");
                        for (int i = 1; i <= columnCount; i++) {
                            resultado.append("<td>").append(rs.getString(i)).append("</td>");
                        }
                        resultado.append("</tr>");
                    }
                    resultado.append("</table>");

                    // Enviar el resultado al cliente
                    response.getWriter().println("Resultado de la consulta:<br>" + resultado.toString());
                } else {
                    // Para otras consultas como INSERT, UPDATE, DELETE
                    int filasAfectadas = stmt.executeUpdate(sentenciaSQL);
                    response.getWriter().println("Sentencia SQL ejecutada exitosamente. Filas afectadas: " + filasAfectadas);
                }

            } catch (SQLException e) {
                // Manejo de errores de SQL
                response.getWriter().println("Error al ejecutar la sentencia SQL: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Desconectar de la base de datos
                Conexion.desconecta();
            }
        } else {
            response.getWriter().println("No se pudo establecer la conexión a la base de datos.");
        }
    }
}
