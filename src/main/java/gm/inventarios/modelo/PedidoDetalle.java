package gm.inventarios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idDetallePedido;

	Integer cantidad;

	@ManyToOne
	Producto producto;

	@ManyToOne
	Pedido pedido;

} 
