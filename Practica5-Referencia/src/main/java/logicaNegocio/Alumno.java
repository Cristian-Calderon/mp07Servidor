package logicaNegocio;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos") // Asegúrate de que coincide con la BD
public class Alumno {

    @Id
    @Column(name = "id") // ❌ Eliminar @GeneratedValue aquí
    private int id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "curso", nullable = false, length = 50)
    private String curso;

    // Constructor vacío requerido por JPA
    public Alumno() {}

    // Constructor con parámetros (para autogenerar ID en BD)
    public Alumno(String nombre, String curso) {
        this.nombre = nombre;
        this.curso = curso;
    }

    // Constructor con ID manual
    public Alumno(int id, String nombre, String curso) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
    }

    // Métodos getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
}
