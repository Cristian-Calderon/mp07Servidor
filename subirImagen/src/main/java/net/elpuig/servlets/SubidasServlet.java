package net.elpuig.servlets;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/subidas")
@MultipartConfig(location="/", fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class SubidasServlet extends HttpServlet {
 
   private static final long serialVersionUID = 1L;

   /**
    * Nombre del directorio en el que se almacenarán los ficheros recibidos.
    * Este directorio DEBE existir en webapp/img-subidas. Ojo! Desde el Eclipse 
    * no veremos aquí ninguna imagen subida porque mientras estemos desarrollando 
    * en el IDE la ruta será la ubicación de la aplicación desplegada en el Tomcat 
    * interno del Eclipse, algo así (caso de Windows):
    *  C:\Users\Usuario\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\subirImagenes\img-subidas
    */
   private static final String DIR_SUBIDAS = "img-subidas";

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException 
   {
      // Obtener la ruta absoluta a la aplicación
      String applicationPath = request.getServletContext().getRealPath("");
      
      // Construir la ruta hacia el directorio en el que se guardan las imagenes subidas
      String uploadFilePath = applicationPath + File.separator + DIR_SUBIDAS;

      String nombre = request.getParameter("nombre");
      System.out.println("Nombre: " + nombre);
      
      String descripcion = request.getParameter("descripcion");
      System.out.println("descripcion: " + descripcion);

      Part archivo = request.getPart("imagen");

      System.out.println("part.getContentType : " + archivo.getContentType());
      System.out.println("part.getSize : " + archivo.getSize());
      System.out.println("part.getName : " + archivo.getName());
      System.out.println("part.getSubmittedFileName : " + archivo.getSubmittedFileName());
            
      // Usaremos el nombre del fichero físico recibido para guardarlo en el servidor
      String nom = archivo.getSubmittedFileName();
      
      /*
      
      // Podriamos validar el tipo de archivo de la siguiente forma.    
      
      if (nom.endsWith("pdf") || nom.endsWith("doc") || nom.endsWith("docx")) {
         // archivo valido
      }else{
         // archivo no permitido
      }
      
      */
      // Escribimos el archivo al disco duro del servidor
      archivo.write(uploadFilePath + File.separator + nom);
      request.setAttribute("mensaje", "La subida del fichero ha concluido!");
      getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request, response);
   }
   
}

/*
 * The @MultipartConfig annotation supports the following optional attributes:

	-location: An absolute path to a directory on the file system. The location attribute does not 
	support a path relative to the application context. This location is used to store files 
	temporarily while the parts are processed or when the size of the file exceeds the specified 
	fileSizeThreshold setting. The default location is "".

	-fileSizeThreshold: The file size in bytes after which the file will be temporarily stored on disk. 
	The default size is 0 bytes.

	-MaxFileSize: The maximum size allowed for uploaded files, in bytes. If the size of any uploaded file 
	is greater than this size, the web container will throw an exception (IllegalStateException). The 
	default size is unlimited.

	-maxRequestSize: The maximum size allowed for a multipart/form-data request, in bytes. The web 
	container will throw an exception if the overall size of all uploaded files exceeds this threshold. 
	The default size is unlimited.
*/

