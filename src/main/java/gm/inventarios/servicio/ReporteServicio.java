package gm.inventarios.servicio;

import gm.inventarios.modelo.ReporteVentasDTO;
import gm.inventarios.pdf.GenerarReporteVentasPDF;
import gm.inventarios.repositorio.ReporteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServicio {

	private final ReporteRepositorio reporteRepositorio;

	public ReporteServicio(ReporteRepositorio reporteRepositorio) {
		this.reporteRepositorio = reporteRepositorio;
	}

	public List<ReporteVentasDTO> obtenerTotalesPorDia() {
		return reporteRepositorio.obtenerTotalesPorDia();
	}
	
	public void generarPDF() throws Exception {
        List<ReporteVentasDTO> reporte = reporteRepositorio.obtenerTotalesPorDia();
        GenerarReporteVentasPDF.generarPDF(reporte, "ReporteVentasDiarias.pdf");
    }
}
