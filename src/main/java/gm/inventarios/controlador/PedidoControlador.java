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

import gm.inventarios.modelo.Pedido;
import gm.inventarios.modelo.PedidoDetalle;
import gm.inventarios.servicio.IPedidoServicio;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class PedidoControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(PedidoControlador.class);

    @Autowired
    private IPedidoServicio pedidoServicio;

    // GET: listar todos los pedidos con detalles
    @GetMapping("/pedidos")
    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = this.pedidoServicio.listarPedidos();
        logger.info("Pedidos obtenidos:");
        pedidos.forEach(p -> logger.info(p.toString()));
        return pedidos;
    }

    // GET: buscar pedido por ID
    @GetMapping("/pedidos/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {
        return this.pedidoServicio.buscarPedidoPorId(id);
    }

    // POST: crear pedido
    @PostMapping("/pedidos")
    public void agregarPedido(@RequestBody Pedido pedido) {
    	 // Asignar el pedido a cada detalle
        if (pedido.getDetalles() != null) {
            for (PedidoDetalle detalle : pedido.getDetalles()) {
                detalle.setPedido(pedido);
            }
        }

        this.pedidoServicio.guardarPedido(pedido);
        logger.info("Pedido agregado: {}", pedido);
    }

    // PUT: actualizar pedido
    @PutMapping("/pedidos/{id}")
    public void actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido existente = this.pedidoServicio.buscarPedidoPorId(id);
        if (existente != null) {
        	pedido.setIdPedido(id);
            this.pedidoServicio.guardarPedido(pedido);
            logger.info("Pedido actualizado: {}", pedido);
        } else {
            logger.warn("Intento de actualizar pedido inexistente con id {}", id);
        }
    }

    // DELETE: eliminar pedido
    @DeleteMapping("/pedidos/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        this.pedidoServicio.eliminarPedidoPorId(id);
        logger.info("Pedido eliminado con id {}", id);
    }
}
