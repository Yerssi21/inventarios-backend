package gm.inventarios.controlador;

import gm.inventarios.modelo.ReporteVentasDTO;
import gm.inventarios.servicio.ReporteServicio;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteControlador {

	private final ReporteServicio reporteServicio;

	public ReporteControlador(ReporteServicio reporteServicio) {
		this.reporteServicio = reporteServicio;
	}

	@GetMapping("/ventas-por-dia")
	public List<ReporteVentasDTO> obtenerReporteVentasPorDia() {
		return reporteServicio.obtenerTotalesPorDia();
	}
	
	 /**
     * Endpoint para generar y descargar el reporte PDF
     * Devuelve el archivo PDF como un ResponseEntity<byte[]>
     */
//    @GetMapping("/pdf")
//    public ResponseEntity<byte[]> descargarReportePDF() throws Exception {
//        String nombreArchivo = "ReporteVentasDiarias.pdf";
//
//        // Genera el PDF en el servidor
//        reporteServicio.generarPDF();
//
//        // Carga el archivo en memoria
//        File pdfFile = new File(nombreArchivo);
//        byte[] contenido = Files.readAllBytes(pdfFile.toPath());
//
//        // Retorna el PDF como archivo descargable
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName())
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(contenido);
//    }
	
	@GetMapping("/pdf")
	public ResponseEntity<byte[]> descargarReportePDF() {
	    try {
	        String nombreArchivo = "ReporteVentasDiarias.pdf";
	        reporteServicio.generarPDF();

	        File pdfFile = new File(nombreArchivo);
	        if (!pdfFile.exists()) {
	            return ResponseEntity.notFound().build();
	        }

	        byte[] contenido = Files.readAllBytes(pdfFile.toPath());

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName())
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(contenido);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.internalServerError().build();
	    }
	}
	
}
