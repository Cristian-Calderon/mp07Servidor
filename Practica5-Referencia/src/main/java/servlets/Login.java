package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import beans.Usuario;

import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final String PG_ERROR = "/error.jsp";
    private static final String PG_ALTA = "/Controlador?operacion=alta";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String usuarioIntro = request.getParameter("txtUsuario");
        String passwIntro = request.getParameter("txtContrasenya");

        HttpSession sesion = request.getSession();
        String sessionUser = (String) sesion.getAttribute("nombre");

        String user = (sessionUser == null) ? validarUsuario(usuarioIntro, passwIntro) : sessionUser;

        if (user == null) {
            request.getRequestDispatcher(PG_ERROR).forward(request, response);
        } else {
            if (sessionUser == null) {
                Usuario usuario = new Usuario(user);
                sesion.setAttribute("usuario", usuario);
                sesion.setAttribute("nombre", usuario.getNombre());
            }
            response.sendRedirect(PG_ALTA);
        }
    }

    private String validarUsuario(String usuarioIntro, String passwIntro) {
        if ("daniel".equals(usuarioIntro) && "daniel".equals(passwIntro)) {
            return usuarioIntro;
        }
        return null;
    }
}
