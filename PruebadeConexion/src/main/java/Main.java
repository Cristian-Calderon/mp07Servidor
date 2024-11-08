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
//		PreparedStatement sirve para un insert
//		PreparedStatement myStamt = null;
		Statement myStamt = null;

		ResultSet myRest = null;

		try {
//			Aca le pasamos los 3 parametros url, usuario, contrasena
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "daw2", "secreto");

//			Error en la terminal el proyecto sql no deja tener no null, los campos eran obligatorios.
//			String sql = ("INSERT INTO employees (first_name, pa_surname) VALUES (?, ?)");
//			myStamt = myConn.prepareStatement(sql);
//			myStamt.setString(1, "Cristian");
//			myStamt.setString(2, "Calderon");

			myStamt = myConn.createStatement();


//			int rowsAffected = myStamt.executeUpdate( "UPDATE employees set email='ccalderon@dundermifflin.com' WHERE first_name = 'Cristian'");
			int rowsAffected = myStamt.executeUpdate("DELETE FROM employees WHERE first_name = 'Cristian'");

			// myRest = myStamt.executeQuery("SELECT * FROM employees");

			myRest = myStamt.executeQuery("SELECT * FROM employees");
			while (myRest.next()) {
				System.out.println(myRest.getString("first_name")+ " " + (myRest.getString("email")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No funciona");
		}

	}

}
