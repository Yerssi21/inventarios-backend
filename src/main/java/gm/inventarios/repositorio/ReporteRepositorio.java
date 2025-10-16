package gm.inventarios.repositorio;

import gm.inventarios.modelo.ReporteVentasDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReporteRepositorio {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Obtiene totales de ventas por día. Usa consulta nativa para asegurar
	 * compatibilidad con DATE(...) en la mayoría DB.
	 */
	public List<ReporteVentasDTO> obtenerTotalesPorDia() {
		String sql = "SELECT DATE(fecha) as dia, SUM(total) as total " + "FROM factura " + "GROUP BY DATE(fecha) "
				+ "ORDER BY DATE(fecha)";

		Query q = em.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = q.getResultList();

		return resultados.stream().map(fila -> {
			// fila[0] -> java.sql.Date (o String dependiendo del driver)
			// fila[1] -> Number (BigDecimal / Double)
			LocalDate fecha = null;
			if (fila[0] instanceof Date) {
				fecha = ((Date) fila[0]).toLocalDate();
			} else if (fila[0] instanceof java.sql.Timestamp) {
				fecha = ((java.sql.Timestamp) fila[0]).toLocalDateTime().toLocalDate();
			} else if (fila[0] != null) {
				// fallback si el driver devuelve String
				fecha = LocalDate.parse(fila[0].toString());
			}

			Double total = null;
			if (fila[1] instanceof Number) {
				total = ((Number) fila[1]).doubleValue();
			} else if (fila[1] != null) {
				total = Double.parseDouble(fila[1].toString());
			} else {
				total = 0.0;
			}

			return new ReporteVentasDTO(fecha, total);
		}).collect(Collectors.toList());
	}
}
