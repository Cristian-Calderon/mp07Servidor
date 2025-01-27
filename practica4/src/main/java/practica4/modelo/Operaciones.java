package practica4.modelo;

import practica4.javabeans.Mensaje;
import java.sql.*;
import java.util.ArrayList;

public class Operaciones {

    public synchronized void grabaMensaje(Mensaje m) {
        String sql = "INSERT INTO mensajes (remitente, destinatario, texto) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, m.getRemite());
            stmt.setString(2, m.getDestino());
            stmt.setString(3, m.getTexto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized ArrayList<Mensaje> obtenerMensajes(String destino) {
        ArrayList<Mensaje> mensajesEncontrados = new ArrayList<>();
        String sql = "SELECT remitente, texto FROM mensajes WHERE destinatario = ?";
        
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, destino);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String remitente = rs.getString("remitente");
                String texto = rs.getString("texto");
                mensajesEncontrados.add(new Mensaje(remitente, destino, texto));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return mensajesEncontrados;
    }
}
