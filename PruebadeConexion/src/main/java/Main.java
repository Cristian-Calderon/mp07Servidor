import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

// <h2>Alt + shift + Y = Toggle Word Wrap on Eclipse</h2>
// <h2>Para identar el codigo: Ctrl + Shift + F</h2>
public class Main {

	public static void main(String[] args) {
	
		Connection myConn = null;
		Statement myStamt = null;
		ResultSet myRest = null;
		try {
//			Aca le pasamos los 3 parametros url, usuario, contrasena
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","daw2", "secreto");
			
		myStamt = myConn.createStatement();
		myRest = myStamt.executeQuery("SELECT * FROM employees");
			
		while(myRest.next()) {
			System.out.println(myRest.getString("first_name"));
		}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("No funciona");
		}
		

	}

}
