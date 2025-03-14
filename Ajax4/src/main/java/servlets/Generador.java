package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/Generador")
public class Generador extends HttpServlet {
	
	@Override
	public void  init () throws ServletException{
		super.init();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			
		}
	}
	
	private static final long serialVersionUID = 1L;
       
  
	private static final String URL =
//			"jdbc:mysql://localhost:3306/dbpalabras?user=daw2&password=secreto";
			"jdbc:mysql://localhost:3306/dbpalabras?user=daw2&password=secreto";

	   private static final String SQL =
			"SELECT palabra FROM palabras WHERE palabra LIKE ? ORDER BY palabra";
	
    
	   public Generador() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
	       try (PrintWriter out = response.getWriter()) {
//	    	   Llega desde from
	       	String dato = request.getParameter("texto");
	      
		        try (Connection conn = DriverManager.getConnection(URL);
		        		PreparedStatement pstmt = conn.prepareStatement(SQL))
		        {
		        	pstmt.setString(1, dato + "%");
		        	ResultSet rs = pstmt.executeQuery();	
		        	out.println(rs.next() ? rs.getString("palabra") : "");
		         rs.close();
		        } catch(Exception e) {
		        	e.printStackTrace();
		        }
	       }
	}

}
