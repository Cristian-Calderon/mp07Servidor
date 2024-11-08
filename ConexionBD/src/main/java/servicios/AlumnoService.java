package servicios;

import java.util.ArrayList;
import java.util.List;

import modelos.Alumno;

public class AlumnoService {

	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	public static void addAlumno (String id, String curso, String nombre) {
	Alumno nuevoAlumno = new Alumno(id, curso, nombre);
	alumnos.add(nuevoAlumno);
}
	
	public static List<Alumno> verAlumnos(){
		return new ArrayList<>(alumnos);
	}
}
