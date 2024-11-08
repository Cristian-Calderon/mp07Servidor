package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Alumno;
import servicios.AlumnoService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class SQLExecutorServlet
 */
@WebServlet("/SQLExecutorServlet")
public class SQLExecutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SQLExecutorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        // Print dinamico para pantalla         
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        if ("alta".equals(action)) {
            // Obtener los datos del alumno del formulario
            String id = request.getParameter("id");
            String curso = request.getParameter("curso");
            String nombre = request.getParameter("nombre");

            // Agregar alumno al "sistema"
            AlumnoService.addAlumno(id, curso, nombre);
            out.println("<p>Alumno dado de alta correctamente.</p>");

        } else {
            // Obtener y mostrar los datos de todos los alumnos
            out.print("<table>");
            out.println("<p>Recuperando registros de alumnos</p>");
            out.println("<tr><td>Id</td><td>Curso</td><td>Nombre</td></tr>");

            List<Alumno> alumnos = AlumnoService.verAlumnos();
            for (Alumno alumno : alumnos) {
                out.print("<tr>");
                out.print("<td>" + alumno.getId() + "</td>");
                out.print("<td>" + alumno.getCurso() + "</td>");
                out.print("<td>" + alumno.getNombre() + "</td>");
                out.print("</tr>");
            }
            out.print("</table>");
        }
    }
}