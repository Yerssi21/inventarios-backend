package gm.inventarios.controlador;

import gm.inventarios.modelo.Producto;
import gm.inventarios.servicio.ProductoServicio;
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

import java.util.List;

@RestController
//http://locahost:8080/inventario-app
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductoControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    //http://locahost:8080/inventario-app/productos
    @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos:");
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }
    
    // GET: buscar por ID
    @GetMapping("/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return this.productoServicio.buscarProductoPorId(id);
    }

    // POST: crear producto
    @PostMapping("/productos")
    public void agregarProducto(@RequestBody Producto producto) {
        this.productoServicio.guardarProducto(producto);
        logger.info("Producto agregado: {}", producto);
    }

    // PUT: actualizar producto
    @PutMapping("/productos/{id}")
    public void actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existente = this.productoServicio.buscarProductoPorId(id);
        if (existente != null) {
            producto.setIdProducto(id);
            this.productoServicio.guardarProducto(producto);
            logger.info("Producto actualizado: {}", producto);
        } else {
            logger.warn("Intento de actualizar producto inexistente con id {}", id);
        }
    }

    // DELETE: eliminar producto
    @DeleteMapping("/productos/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        this.productoServicio.eliminarProductoPorId(id);
        logger.info("Producto eliminado con id {}", id);
    }

}
