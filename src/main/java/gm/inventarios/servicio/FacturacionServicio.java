package gm.inventarios.servicio;

import gm.inventarios.modelo.Factura;
import gm.inventarios.modelo.MetodoPago;
import gm.inventarios.modelo.Pedido;
import gm.inventarios.pdf.GenerarFacturaPDF;
import gm.inventarios.repositorio.FacturaRepositorio;
import gm.inventarios.repositorio.PedidoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FacturacionServicio {

    private final FacturaRepositorio facturaRepositorio;
    private final PedidoRepositorio pedidoRepositorio;

    public FacturacionServicio(FacturaRepositorio facturaRepositorio, PedidoRepositorio pedidoRepositorio) {
        this.facturaRepositorio = facturaRepositorio;
        this.pedidoRepositorio = pedidoRepositorio;
    }

    /**
     * 🧾 Genera una factura para un pedido.
     * Si ya existe, la devuelve directamente.
     */
    @Transactional
    public Factura generarFactura(Long idPedido, String metodoPago) {
        System.out.println("➡ Generando factura para pedido ID: " + idPedido + " con método: " + metodoPago);

        // ✅ Cargar pedido con detalles y productos (usando JOIN FETCH)
        Pedido pedido = pedidoRepositorio.findPedidoConDetalles(idPedido);
        if (pedido == null) {
            throw new RuntimeException("Pedido no encontrado con ID: " + idPedido);
        }

        // ✅ Verificar si ya existe una factura para este pedido
        Factura facturaExistente = facturaRepositorio.findByPedidoIdPedido(idPedido);
        if (facturaExistente != null) {
            System.out.println("ℹ Factura existente encontrada: " + facturaExistente.getIdFactura());
            return facturaExistente;
        }

        // ✅ Calcular el total del pedido
        double total = pedido.getDetalles() != null
                ? pedido.getDetalles().stream()
                    .mapToDouble(d -> d.getCantidad() * d.getProducto().getPrecio())
                    .sum()
                : 0.0;

        System.out.println("💰 Total calculado para la factura: " + total);

        // ✅ Crear nueva factura
        Factura factura = new Factura();
        factura.setFecha(LocalDateTime.now());
        factura.setTotal(total);
        factura.setPedido(pedido);

        // ✅ Convertir String del frontend a Enum
        try {
            factura.setMetodoPago(MetodoPago.valueOf(metodoPago.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Método de pago no válido: " + metodoPago);
        }

        // ✅ Guardar factura
        facturaRepositorio.save(factura);
        System.out.println("✅ Factura generada correctamente con ID: " + factura.getIdFactura());

        return factura;
    }

    /**
     * 📄 Genera y devuelve el PDF de la factura (crea la factura si no existe).
     */
    @Transactional
    public byte[] generarFacturaPDF(Long idPedido, String metodoPago) throws Exception {
        System.out.println("📄 Generando PDF para factura del pedido ID: " + idPedido);
        Factura factura = generarFactura(idPedido, metodoPago);
        byte[] pdfBytes = GenerarFacturaPDF.generarFacturaPDFBytes(factura);
        System.out.println("✅ PDF generado correctamente para la factura ID: " + factura.getIdFactura());
        return pdfBytes;
    }
}