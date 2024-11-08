package modelos;

public class Alumno {

	private String id;
	private String curso;
	private String nombre;
	
	
	public Alumno(String id, String curso, String nombre) {
		this.id = id;
		this.curso = curso;
		this.nombre = nombre;
	}


	public String getId() {
		return id;
	}


	public String getCurso() {
		return curso;
	}


	public String getNombre() {
		return nombre;
	}
	


	
}
