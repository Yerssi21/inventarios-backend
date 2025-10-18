package gm.inventarios.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idFactura;

	Double total;
	@Enumerated(EnumType.STRING)
	private MetodoPago metodoPago;
	LocalDateTime fecha;

	@OneToOne
	Pedido pedido;

}
