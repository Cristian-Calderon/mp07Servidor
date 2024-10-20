package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Servlet implementation class ImageServelt
 */
@WebServlet("/handler")
public class ImageServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("image/jpeg");

		String pathToWeb = getServletContext().getRealPath(File.separator + "imagenes");
		System.out.println(pathToWeb);
		File f = new File(pathToWeb + "/jakarta_logo.png");
		BufferedImage bi = ImageIO.read(f);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "png", out);
		out.close();

	}

}
