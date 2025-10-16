package gm.inventarios.servicio;

import java.util.List;

import gm.inventarios.modelo.PedidoDetalle;

public interface IPedidoDetalleServicio {
	List<PedidoDetalle> listarPedidos();

	PedidoDetalle buscarPedidoPorId(Long idPedido);

	void guardarPedido(PedidoDetalle pedido);

	void eliminarPedidoPorId(Long idPedido);
}
