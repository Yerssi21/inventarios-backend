package gm.inventarios.servicio;

import gm.inventarios.modelo.Pedido;
import gm.inventarios.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServicio implements IPedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Override
    public List<Pedido> listarPedidos() {
        // Ahora trae también los detalles y productos
        return pedidoRepositorio.findAllConDetalles();
    }

    @Override
    public Pedido buscarPedidoPorId(Long idPedido) {
        return pedidoRepositorio.findPedidoConDetalles(idPedido);
    }

    @Override
    public void guardarPedido(Pedido pedido) {
        this.pedidoRepositorio.save(pedido);
    }

    @Override
    public void eliminarPedidoPorId(Long idPedido) {
        this.pedidoRepositorio.deleteById(idPedido);
    }
}