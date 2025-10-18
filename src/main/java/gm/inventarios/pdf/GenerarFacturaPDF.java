package gm.inventarios.pdf;

import gm.inventarios.modelo.Factura;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clase para generar facturas en formato PDF.
 * Contiene dos métodos:
 *  - generarFacturaPDFBytes(): genera el PDF en memoria (byte[])
 *  - generarFacturaPDF(): guarda el PDF físicamente en disco
 */
public class GenerarFacturaPDF {

    /**
     * Genera el PDF de una factura y lo devuelve en memoria (byte[]).
     * Ideal para enviar al frontend o devolver en un ResponseEntity.
     */
    public static byte[] generarFacturaPDFBytes(Factura factura) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Encabezado
        document.add(new Paragraph("====================================="));
        document.add(new Paragraph("           FACTURA DE VENTA          "));
        document.add(new Paragraph("=====================================\n"));

        // Datos generales
        document.add(new Paragraph("Factura N°: " + factura.getIdFactura()));
        document.add(new Paragraph("Fecha: " + factura.getFecha()));
        document.add(new Paragraph("Método de pago: " + factura.getMetodoPago()));
        document.add(new Paragraph("Mesa: " + factura.getPedido().getMesa() + "\n"));

        // Detalles de los productos
        document.add(new Paragraph("---- DETALLES ----"));
        factura.getPedido().getDetalles().forEach(d -> {
            double subtotal = d.getCantidad() * d.getProducto().getPrecio();
            document.add(new Paragraph(
                    d.getProducto().getNombre() +
                    "  x" + d.getCantidad() +
                    "  =  $" + subtotal
            ));
        });

        // Total final
        document.add(new Paragraph("\nTOTAL: $" + factura.getTotal()));
        document.add(new Paragraph("====================================="));

        document.close();
        return baos.toByteArray();
    }

    /**
     * Genera el PDF y lo guarda en disco.
     * Ideal si quieres tener una copia física del archivo en el servidor.
     */
    public static void generarFacturaPDF(Factura factura) throws Exception {
        byte[] pdfBytes = generarFacturaPDFBytes(factura);
        String nombreArchivo = "factura_" + factura.getIdFactura() + ".pdf";
        Files.write(Paths.get(nombreArchivo), pdfBytes);
        System.out.println("✅ PDF generado y guardado: " + nombreArchivo);
    }
}