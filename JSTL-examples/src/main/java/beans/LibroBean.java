package beans;

public class LibroBean {
//	Propiedad indexada => 
	private String[] autor = new String[3]; // 3 autores como maximo
	private String titulo;

	public String getAutor(int pos) {
		return this.autor[pos];
	}

	public void setAutor(String autor, int pos) {
		this.autor[pos] = autor;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
