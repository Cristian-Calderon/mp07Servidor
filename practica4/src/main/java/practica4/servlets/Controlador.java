package practica4.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import practica4.javabeans.*;
import practica4.modelo.*;

public class Controlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getServletPath();
        Operaciones oper = new Operaciones();

        if (op.equals("/envio.go")) {
            RequestDispatcher rd = request.getRequestDispatcher("envio");
            rd.forward(request, response);
        } else if (op.equals("/grabar.go")) {
            Mensaje men = (Mensaje) request.getAttribute("mensa");
            oper.grabaMensaje(men);
            response.sendRedirect("index.html");
        } else if (op.equals("/muestra.go")) {
            response.sendRedirect("mostrar.html");
        } else if (op.equals("/ver.go")) {
            ArrayList<Mensaje> mensajes = oper.obtenerMensajes(request.getParameter("nombre"));
            request.setAttribute("mensajes", mensajes);
            RequestDispatcher rd = request.getRequestDispatcher("ver");
            rd.forward(request, response);
        }
    }
}
