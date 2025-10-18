package gm.inventarios.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetallePedido;

    private Integer cantidad;

    @ManyToOne
    @ToString.Exclude  // 👈 Evita recursion infinita al imprimir
    private Producto producto;

    @ManyToOne
    @JsonBackReference // 👈 Indica la parte "inversa" del vínculo
    @ToString.Exclude
    private Pedido pedido;
}
