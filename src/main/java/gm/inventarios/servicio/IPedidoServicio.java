package gm.inventarios.servicio;

import java.util.List;

import gm.inventarios.modelo.Pedido;

public interface IPedidoServicio {

	List<Pedido> listarPedidos();

	Pedido buscarPedidoPorId(Long idPedido);

	void guardarPedido(Pedido pedido);

	void eliminarPedidoPorId(Long idPedido);
}
