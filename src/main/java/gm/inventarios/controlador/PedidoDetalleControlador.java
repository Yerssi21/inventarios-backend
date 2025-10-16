package gm.inventarios.controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gm.inventarios.modelo.PedidoDetalle;
import gm.inventarios.servicio.IPedidoDetalleServicio;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class PedidoDetalleControlador {
	private static final Logger logger = LoggerFactory.getLogger(PedidoControlador.class);

	@Autowired
	private IPedidoDetalleServicio pedidoDetalleServicio;

	// GET: listar pedidos
	@GetMapping("/detallePedidos")
	public List<PedidoDetalle> obtenerPedidos() {
		List<PedidoDetalle> pedidos = this.pedidoDetalleServicio.listarPedidos();
		logger.info("Pedidos obtenidos:");
		pedidos.forEach(p -> logger.info(p.toString()));
		return pedidos;
	}

	// GET: buscar pedido por ID
	@GetMapping("/detallePedidos/{id}")
	public PedidoDetalle obtenerPedidoPorId(@PathVariable Long id) {
		return this.pedidoDetalleServicio.buscarPedidoPorId(id);
	}

	// POST: crear pedido
	@PostMapping("/detallePedidos")
	public void agregarPedido(@RequestBody PedidoDetalle pedido) {
		this.pedidoDetalleServicio.guardarPedido(pedido);
		logger.info("Pedido agregado: {}", pedido);
	}

	// PUT: actualizar pedido
	@PutMapping("/detallePedidos/{id}")
	public void actualizarPedido(@PathVariable Long id, @RequestBody PedidoDetalle pedido) {
		PedidoDetalle existente = this.pedidoDetalleServicio.buscarPedidoPorId(id);
		if (existente != null) {
			pedido.setIdDetallePedido(id);
			this.pedidoDetalleServicio.guardarPedido(pedido);
			logger.info("Pedido actualizado: {}", pedido);
		} else {
			logger.warn("Intento de actualizar pedido inexistente con id {}", id);
		}
	}

	// DELETE: eliminar pedido
	@DeleteMapping("/detallePedidos/{id}")
	public void eliminarPedido(@PathVariable Long id) {
		this.pedidoDetalleServicio.eliminarPedidoPorId(id);
		logger.info("Pedido eliminado con id {}", id);
	}

}
