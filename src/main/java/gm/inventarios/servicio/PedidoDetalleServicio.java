package gm.inventarios.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gm.inventarios.modelo.PedidoDetalle;
import gm.inventarios.repositorio.PedidoDetalleRepositorio;
@Service
public class PedidoDetalleServicio implements IPedidoDetalleServicio {

	@Autowired
	private PedidoDetalleRepositorio pedidoDetalleRepositorio;

	@Override
	public List<PedidoDetalle> listarPedidos() {
		return this.pedidoDetalleRepositorio.findAll();
	}

	@Override
	public PedidoDetalle buscarPedidoPorId(Long idPedido) {
		return this.pedidoDetalleRepositorio.findById(idPedido).orElse(null);
	}

	@Override
	public void guardarPedido(PedidoDetalle pedido) {
		this.pedidoDetalleRepositorio.save(pedido);

	}

	@Override
	public void eliminarPedidoPorId(Long idPedido) {
		this.pedidoDetalleRepositorio.deleteById(idPedido);
	}

}
