package servlets;

import jdbc.Alumno;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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

        String action = request.getParameter("action");

        try {
            if ("alta".equals(action)) {
                // Crear nuevo alumno desde los datos del formulario
                String id = request.getParameter("id");
                String curso = request.getParameter("curso");
                String nombre = request.getParameter("nombre");

                Alumno alumno = new Alumno(id, curso, nombre);
                alumno.save(); // Guarda el nuevo alumno en la base de datos

                out.println("<p>Alumno dado de alta correctamente.</p>");
            } else {
                List<Alumno> alumnos = Alumno.load();

                // Imprime los datos en formato tabla
                out.print("<table>");
                out.println("<p>Listado de alumnos</p>");
                out.println("<tr><td>Id</td><td>Curso</td><td>Nombre</td></tr>");
                for (Alumno alumno : alumnos) {
                    out.print("<tr>");
                    out.print("<td>" + alumno.getId() + "</td>");
                    out.print("<td>" + alumno.getCurso() + "</td>");
                    out.print("<td>" + alumno.getNombre() + "</td>");
                    out.print("</tr>");
                }
                out.print("</table>");
            }
        } catch (SQLException e) {
            out.println("<p>Error en la operación: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}
