package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private String id;
    private String curso;
    private String nombre;

    public Alumno(String id, String curso, String nombre) {
        this.id = id;
        this.curso = curso;
        this.nombre = nombre;
    }

    public String getId() { return id; }
    public String getCurso() { return curso; }
    public String getNombre() { return nombre; }

    // Método para cargar todos los alumnos desde la BD
    public static List<Alumno> load() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT id, curso, nombre FROM alumnos";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("id");
                String curso = rs.getString("curso");
                String nombre = rs.getString("nombre");
                alumnos.add(new Alumno(id, curso, nombre));
            }
        }

        return alumnos;
    }

    // Método para guardar un nuevo alumno en la BD
    public void save() throws SQLException {
        String insertQuery = "INSERT INTO alumnos (id, curso, nombre) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, this.id);
            pstmt.setString(2, this.curso);
            pstmt.setString(3, this.nombre);
            pstmt.executeUpdate();
        }
    }
}
