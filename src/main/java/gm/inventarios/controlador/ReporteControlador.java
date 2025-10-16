package gm.inventarios.controlador;

import gm.inventarios.modelo.ReporteVentasDTO;
import gm.inventarios.servicio.ReporteServicio;
import org.springframework.web.bind.annotation.*;

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
}
