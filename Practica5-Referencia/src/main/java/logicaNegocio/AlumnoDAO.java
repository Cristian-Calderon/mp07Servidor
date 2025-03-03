package logicaNegocio;

import java.util.List;

import jakarta.persistence.*;

public class AlumnoDAO {
    private static final EntityManagerFactory emf;

    static {
        try {
            System.out.println("🟢 Intentando crear EntityManagerFactory con AlumnosPU...");
            emf = Persistence.createEntityManagerFactory("AlumnosPU");
            System.out.println("✅ EntityManagerFactory creado con éxito.");
        } catch (Exception e) {
            System.err.println("❌ Error creando EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public void guardarAlumno(Alumno alumno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Verifica si el ID ya existe en la base de datos
            Alumno alumnoExistente = em.find(Alumno.class, alumno.getId());

            if (alumnoExistente == null) {
                // Si no existe, se guarda como nuevo registro
                em.persist(alumno);
            } else {
                // Si ya existe, se actualiza con merge()
                em.merge(alumno);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public List<Alumno> listarAlumnos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
        } finally {
            em.close();
        }
    }
}
