package servlets;

import jdbc.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SQLExecutorServlet")
public class SQLExecutorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtener la sentencia SQL desde el formulario
        String sqlQuery = request.getParameter("sqlQuery");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement()) {

            // Ejecutar la sentencia SQL
            if (sqlQuery.toLowerCase().startsWith("select")) {
                ResultSet rs = stmt.executeQuery(sqlQuery);
                // Mostrar el resultado de la consulta
                out.println("<h3>Usa JDBC para recuperar registros de una tabla:</h3>");
             

                // Obtener el número de columnas
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    out.println(rs.getMetaData().getColumnName(i));
                }
                

                // Mostrar las filas del ResultSet
                while (rs.next()) {
                
                    for (int i = 1; i <= columnCount; i++) {
                        out.println(rs.getString(i));
                    }
                    
                }
                
            } else {
                // Para cualquier otra sentencia (INSERT, UPDATE, DELETE)
                int result = stmt.executeUpdate(sqlQuery);
                out.println("<h3>Sentencia ejecutada correctamente. Filas afectadas: " + result + "</h3>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>Error al ejecutar la sentencia SQL</h3>");
        }
    }
}
