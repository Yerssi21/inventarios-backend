package gm.inventarios.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import gm.inventarios.modelo.ReporteVentasDTO;

import java.util.List;
import java.time.format.DateTimeFormatter;

public class GenerarReporteVentasPDF {

    public static void generarPDF(List<ReporteVentasDTO> reporte, String rutaArchivo) throws Exception {
        // Crear escritor y documento
        PdfWriter writer = new PdfWriter(rutaArchivo);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Título
        document.add(new Paragraph("Reporte de Ventas Diarias").setBold().setFontSize(16));
        document.add(new Paragraph("------------------------------------------------------"));

        // Tabla con 2 columnas: Fecha y Total
        Table tabla = new Table(UnitValue.createPercentArray(new float[]{2, 2}))
                .useAllAvailableWidth();

        // Encabezados
        tabla.addCell("Fecha");
        tabla.addCell("Total");

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Agregar filas del reporte
        for (ReporteVentasDTO fila : reporte) {
            tabla.addCell(fila.getFecha().format(formato));
            tabla.addCell(String.format("%.2f", fila.getTotal()));
        }

        document.add(tabla);
        document.close();

        System.out.println("PDF generado en: " + rutaArchivo);
    }
}
