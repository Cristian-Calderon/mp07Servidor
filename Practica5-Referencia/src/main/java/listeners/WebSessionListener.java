package listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class WebSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("Sesión creada: " + event.getSession().getId());
        
        ServletContext contexto = event.getSession().getServletContext();

        synchronized (contexto) {
            Integer usuariosConectados = (Integer) contexto.getAttribute("usuariosConectados");
            if (usuariosConectados == null) {
                usuariosConectados = 0;
            }
            usuariosConectados++;
            contexto.setAttribute("usuariosConectados", usuariosConectados);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Sesión destruida: " + event.getSession().getId());
        
        ServletContext contexto = event.getSession().getServletContext();

        synchronized (contexto) {
            Integer usuariosConectados = (Integer) contexto.getAttribute("usuariosConectados");
            if (usuariosConectados == null) {
                usuariosConectados = 0;
            }
            usuariosConectados--;
            contexto.setAttribute("usuariosConectados", usuariosConectados);
        }
    }
}
