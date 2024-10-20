package main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


	static final String URL = "jdbc:mysql://10.0.3.1/pruebas";
	static final String SQL = "SELECT * FROM productos";

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try(Connection con = DriverManager.getConnection(URL, "daw2", "secreto");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(SQL))
			{
				while(rs.next()) {
					int id = rs.getInt(1);
					String desc = rs.getString(2);
					double precio = rs.getDouble(3);
					System.out.printf("Id: %d Desc: %s Precio %2f\n\n", id, desc, precio);
				}
			} 
			catch (SQLException e) {

				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException e) {

			e.printStackTrace();
		}
		System.out.println("Fin del programa");
	}

}