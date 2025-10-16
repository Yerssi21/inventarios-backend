package gm.inventarios.servicio;

import gm.inventarios.modelo.Producto;
import java.util.List;

public interface IProductoServicio {
    List<Producto> listarProductos();

    Producto buscarProductoPorId(Long idProducto);

    void guardarProducto(Producto producto);

    void eliminarProductoPorId(Long idProducto);

}
