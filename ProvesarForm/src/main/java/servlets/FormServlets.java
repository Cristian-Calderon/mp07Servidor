package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class FormServlets
 */
@WebServlet("/formulario")
public class FormServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
//		System.out.println(nombre);
		String edad = request.getParameter("edad");
		String [] aficiones = request.getParameterValues("hobbies");

		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String salida = crearSalida(nombre, apellidos, edad,aficiones );
		out.append(salida);
		out.close();	
		
	}

	private String crearSalida(String nombre, String apellidos, String edad, String[] aficiones) {
		// TODO Auto-generated method stub
		
		StringBuilder builder = new StringBuilder();
		builder.append("<h1>acuse de recibo</h1>")
		.append("<h2> Informacion recibida: " + imprimeBonito() + "</h2>")
		.append("<strong>Cliente: </strong>" + nombre + ", "+ apellidos +"<br>")
		.append("<strong>Edad: </strong>" +  edad + "<br>")
		.append("<strong>Aficiones: </strong>");
		
		for (String hobby : aficiones )
			builder.append(hobby).append(", ");
			
		String retorno = builder.toString();
		
			return retorno.substring(0, retorno.length()-2);
		
	}

	
	private String imprimeBonito () {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return sdf.format(new Date());
	}
	
}
