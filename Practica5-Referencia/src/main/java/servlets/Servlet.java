package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logicaNegocio.Alumno;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String sentencia = request.getParameter("sentencia");
	    boolean jstl = "true".equals(request.getParameter("jstl"));

	    try {
	        // Llama al método load y le envía la sentencia
	        List<Alumno> listaAlumnos = Alumno.load(sentencia);

	        if (jstl) {
	            // Si se seleccionó usar JSTL, redirige a la JSP
	            request.setAttribute("listaAlumnos", listaAlumnos);
	            request.getRequestDispatcher("consulta.jsp").forward(request, response);
	        } else {
	            // Genera el HTML directamente en el servlet
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            
	            out.println("<html><body>");
	            out.println("<h2>Resultados de la consulta Servlet:</h2>");
	            out.println("<table border='1'>");
	            out.println("<tr><th>ID</th><th>Nombre</th><th>Curso</th></tr>");

	            for (Alumno alumno : listaAlumnos) {
	                out.println("<tr><td>" + alumno.getId() + "</td><td>" + alumno.getNombre() + "</td><td>"
	                        + alumno.getCurso() + "</td></tr>");
	            }

	            out.println("</table>");
	            out.println("</body></html>");
	        }

	    } catch (ClassNotFoundException e) {
	        if (jstl) {
	            request.setAttribute("error", "Error ejecutando la sentencia: " + e.getMessage());
	            request.getRequestDispatcher("consulta.jsp").forward(request, response);
	        } else {
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("Error ejecutando la sentencia: " + e.getMessage());
	        }
	    }
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtiene los parametros del formulario
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String curso = request.getParameter("curso");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			// Llama al método saved y le manda los parametros
			int filasAfectadas = Alumno.saved(id, nombre, curso);
			// Muestra las filas afectadas
			out.println("Alumno añadido con éxito. Filas afectadas: " + filasAfectadas);

		} catch (ClassNotFoundException e) {
			// Muestra el mensaje de error en la respuesta
			out.println("Error ejecutando la sentencia: " + e.getMessage());
		}
	}

	public static void setURL(String string) {
		// TODO Auto-generated method stub

	}

	public static Connection getConexion() {
		// TODO Auto-generated method stub

		return null;
	}

}