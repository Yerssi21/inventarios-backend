package gm.inventarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gm.inventarios.modelo.Pedido;

public interface PedidoRepositorio  extends JpaRepository<Pedido, Long>{
	
	 // Traer un pedido con detalles y productos
	@Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.detalles d LEFT JOIN FETCH d.producto WHERE p.idPedido = :idPedido")
	Pedido findPedidoConDetalles(@Param("idPedido") Long idPedido);
	
    // Traer todos los pedidos con detalles y productos
    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.detalles d LEFT JOIN FETCH d.producto")
    List<Pedido> findAllConDetalles();
}
