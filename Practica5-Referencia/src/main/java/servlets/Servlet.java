package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logicaNegocio.Alumno;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Controlador")
public class Servlet extends HttpServlet {

    private static final String PG_INFO_SESION = "/infosesion.jsp";
    private static final String PG_DESCONEXION = "/desconectado.jsp";
    private static final String PG_VALIDARSE = "/acceso.jsp";
    private static final String PG_ERROR_VALIDACION = "/errorLogin.jsp";
    private static final String PG_REINSERT_ALTA = "/Controlador?operacion=alta";
    private static final String PG_CONSULTA = "/consulta.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String operacion = request.getParameter("operacion");
        HttpSession sesion = request.getSession(true);
        incrementarContadorSesion(sesion);

        String siguientePag = "/index.html"; // Página por defecto

        try {
            if ("info".equals(operacion)) {
                siguientePag = PG_INFO_SESION;

            } else if ("desconectar".equals(operacion)) {
                sesion.invalidate();
                siguientePag = PG_DESCONEXION;

            } else if ("alta".equals(operacion)) {
                String id = request.getParameter("txtID");
                String curso = request.getParameter("txtCurso");
                String nombre = request.getParameter("txtNombre");

                if (sesion.getAttribute("usuario") != null) {
                    if (id != null && curso != null && nombre != null) {
                        Alumno.saved(Integer.parseInt(id), nombre, curso);
                    } else {
                        request.setAttribute("error", "Faltan datos del alumno.");
                        siguientePag = "/error.jsp";
                    }
                } else {
                    if (id != null && curso != null && nombre != null) {
                        sesion.setAttribute("sesAlumno", id);
                        sesion.setAttribute("sesCurso", curso);
                        sesion.setAttribute("sesNombre", nombre);
                    }
                    siguientePag = PG_VALIDARSE; 
                }

            } else if ("validar".equals(operacion)) {
                String usuarioIntro = request.getParameter("txtUsuario");
                String passwIntro = request.getParameter("txtContrasenya");

                String user = validarUsuario(usuarioIntro, passwIntro);

                if (user == null) {
                    siguientePag = PG_ERROR_VALIDACION;
                } else {
                    sesion.setAttribute("usuario", user); 

                    String id = (String) sesion.getAttribute("sesAlumno");
                    String curso = (String) sesion.getAttribute("sesCurso");
                    String nombre = (String) sesion.getAttribute("sesNombre");

                    if (id != null && curso != null && nombre != null) {
                        Alumno.saved(Integer.parseInt(id), nombre, curso);
                        sesion.removeAttribute("sesAlumno");
                        sesion.removeAttribute("sesCurso");
                        sesion.removeAttribute("sesNombre");
                    }

                    siguientePag = "/index.html";
                }

            } else if ("consulta".equals(operacion)) {
                String sentencia = request.getParameter("sentencia");
                String usarJSTL = request.getParameter("jstl"); 

                if (sentencia != null && !sentencia.trim().isEmpty()) {
                    List<Alumno> alumnos = Alumno.load(sentencia);
                    request.setAttribute("alumnos", alumnos);

                    if ("true".equals(usarJSTL)) {
                        siguientePag = "/consultaJSTL.jsp"; 
                        request.getRequestDispatcher(siguientePag).forward(request, response);
                        return;
                    } else {
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();

                        out.println("<html><head><title>Consulta de Alumnos</title></head><body>");
                        out.println("<h2>Resultados de la consulta:</h2>");
                        out.println("<table border='1'><tr><th>ID</th><th>Nombre</th><th>Curso</th></tr>");

                        for (Alumno alumno : alumnos) {
                            out.println("<tr><td>" + alumno.getId() + "</td>");
                            out.println("<td>" + alumno.getNombre() + "</td>");
                            out.println("<td>" + alumno.getCurso() + "</td></tr>");
                        }

                        out.println("</table><br><a href='index.html'>Volver al Inicio</a>");
                        out.println("</body></html>");

                        out.close();
                        return;
                    }
                } else {
                    request.setAttribute("error", "La consulta no puede estar vacía.");
                    siguientePag = "/error.jsp";
                }
            }

            request.getRequestDispatcher(siguientePag).forward(request, response);

        } catch (ClassNotFoundException e) {
            throw new ServletException("Error de conexión con la base de datos", e);
        }
    }

    private void incrementarContadorSesion(HttpSession sesion) {
        Integer contadorAccesos = 0;
        if (!sesion.isNew()) {
            Integer contadorActual = (Integer) sesion.getAttribute("contadorAccesos");
            if (contadorActual != null) {
                contadorAccesos = contadorActual + 1;
            }
        }
        sesion.setAttribute("contadorAccesos", contadorAccesos);
    }

    private String validarUsuario(String usuarioIntro, String passwIntro) {
        if ("daniel".equals(usuarioIntro) && "daniel".equals(passwIntro)) {
            return usuarioIntro;
        }
        return null;
    }
}
