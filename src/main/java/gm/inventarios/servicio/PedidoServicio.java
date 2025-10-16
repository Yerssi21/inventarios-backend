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
        return this.pedidoRepositorio.findAll();
    }

    @Override
    public Pedido buscarPedidoPorId(Long idPedido) {
        return this.pedidoRepositorio.findById(idPedido).orElse(null);
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
