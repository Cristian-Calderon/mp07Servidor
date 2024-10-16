package servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SvUsuario
 */
@WebServlet("/SvUsuario")
public class SvUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SvUsuario() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		Base de datos ficticia
		List<Usuario> listaUsuarios = new ArrayList<>();
		listaUsuarios.add(new Usuario("37141","Cristian", "Calderon", "2417039"));
		listaUsuarios.add(new Usuario("12","Enrique", "Calderon", "73028998"));
		listaUsuarios.add(new Usuario("37232141","Damaso", "Calderon", "2584156"));
		
//		Tenemos que dar un respuesta en otra pagina 
		HttpSession misesion = request.getSession();
		misesion.setAttribute("listaUsuarios", listaUsuarios);
		
		response.sendRedirect("mostrarUsuarios.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* doGet(request, response); */
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String telefono = request.getParameter("telefono");
		
		System.out.println("Dni es: " +dni);
		System.out.println("Nombre es: " +nombre);
		System.out.println("Apellido es: " +apellido);
		System.out.println("Telefono es: " +telefono);
	}

}
