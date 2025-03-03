package servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logicaNegocio.Alumno;
import logicaNegocio.AlumnoDAO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.*;

public class Report extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 📌 Obtener la lista de alumnos desde JPA
            AlumnoDAO dao = new AlumnoDAO();
            List<Alumno> alumnos = dao.listarAlumnos();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(alumnos);

            // 📌 Localizar el reporte compilado
            File informeCompilado = new File(getServletContext().getRealPath("/WEB-INF/reports/Alumnos.jasper"));

            // 📌 Cargar el informe compilado
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(informeCompilado);

            // 📌 Llenar el reporte con la colección de alumnos (sin JDBC)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

            // 📌 Obtener el tipo de informe seleccionado por el usuario
            String tipoInforme = request.getParameter("optInformes");

            if ("pdf".equals(tipoInforme)) {
                exportarPDF(jasperPrint, response);
            } else if ("excel".equals(tipoInforme)) {
                exportarExcel(jasperPrint, response);
            } else if ("word".equals(tipoInforme)) {
                exportarWord(jasperPrint, response);
            } else if ("html".equals(tipoInforme)) {
                exportarHTML(jasperPrint, response);
            } else {
                // 📌 Si no se especifica un tipo, exportamos por defecto a PDF
                exportarPDF(jasperPrint, response);
            }

        } catch (JRException | IOException e) {
            e.printStackTrace();
            throw new ServletException("Error al generar el informe", e);
        }
    }

    // 📌 Método para exportar a PDF
    private void exportarPDF(JasperPrint jasperPrint, HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=alumnos.pdf");

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        exporter.setConfiguration(new SimplePdfExporterConfiguration());
        exporter.exportReport();
    }

    // 📌 Método para exportar a Excel
    private void exportarExcel(JasperPrint jasperPrint, HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=alumnos.xls");

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(false);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

    // 📌 Método para exportar a Word
    private void exportarWord(JasperPrint jasperPrint, HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "inline; filename=alumnos.doc");

        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        exporter.exportReport();
    }

    // 📌 Método para exportar a HTML
    private void exportarHTML(JasperPrint jasperPrint, HttpServletResponse response) throws IOException, JRException {
        response.setContentType("text/html");
        response.setHeader("Content-Disposition", "inline; filename=alumnos.html");

        HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));

        exporter.exportReport();
    }
}
