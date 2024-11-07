import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

// <h2>Alt + shift + Y = Toggle Word Wrap on Eclipse</h2>
// <h2>Para identar el codigo: Ctrl + Shift + F</h2>
public class Main {

	public static void main(String[] args) {
	
		Connection myConn = null;
//		Statement myStamt = null;
		PreparedStatement myStamt = null;
//		ResultSet myRest = null;
		
		try {
//			Aca le pasamos los 3 parametros url, usuario, contrasena
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","daw2", "secreto");
			
//			Error en la terminal el proyecto sql no deja tener no null, los campos eran obligatorios.
		String sql = ("INSERT INTO employees (first_name, pa_surname,email, salary) VALUES (?, ?, ?, ?)");
		myStamt = myConn.prepareStatement(sql);
		myStamt.setString(1, "Cristian");
		myStamt.setString(2, "Calderon");
		myStamt.setString(3, "ccalderon@gmail.com");
		myStamt.setString(4, "120000");
		
		int rowsAffected = myStamt.executeUpdate();
		System.out.print(rowsAffected);
		
		//myRest = myStamt.executeQuery("SELECT * FROM employees");
			
		if(rowsAffected > 0) {
			System.out.println("Se ha creado un nuevo empleado");
		}
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("No funciona");
		}
		
		

	}

}
