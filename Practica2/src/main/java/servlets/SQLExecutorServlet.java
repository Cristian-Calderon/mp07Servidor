package servlets;

import jdbc.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtener el parámetro de acción del formulario
        String action = request.getParameter("action");

        try (Connection conn = Conexion.getConexion()) {	
        	
        	//
            if ("alta".equals(action)) {
                // Obtener los datos del alumno del formulario
                String id = request.getParameter("id");
                String curso = request.getParameter("curso");
                String nombre = request.getParameter("nombre");

                // Crear y ejecutar el INSERT
                String insertQuery = "INSERT INTO alumnos (id, curso, nombre) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                    pstmt.setString(1, id);
                    pstmt.setString(2, curso);
                    pstmt.setString(3, nombre);
                    
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        out.println("<p>Alumno dado de alta correctamente.</p>");
                    } else {
                        out.println("<p>No se pudo dar de alta al alumno.</p>");
                    }
                }
            } else {
                // Obtener y ejecutar la consulta SQL de selección
                String sqlQuery = request.getParameter("sqlQuery");
                if (sqlQuery.startsWith("select") || sqlQuery.startsWith("SELECT")) {
                    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlQuery)) {
                        out.print("<table>");
                        out.println("<p>Usa JDBC para recuperar registros de una tabla</p>");
                        out.println("<tr><td>Id</td><td>Curso</td><td>Nombre</td></tr>");
                        while (rs.next()) {
                            out.print("<tr>");
                            out.print("<td>" + rs.getString("id") + "</td>");
                            out.print("<td>" + rs.getString("curso") + "</td>");
                            out.print("<td>" + rs.getString("nombre") + "</td>");
                            out.print("</tr>");
                        }
                        out.print("</table>");
                    }
                } else {
                    out.println("<p>Consulta SQL inválida.</p>");
                }
            }
        } catch (SQLException e) {
            out.println("<p>Error en la operación: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}
