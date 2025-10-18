package gm.inventarios.controlador;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gm.inventarios.modelo.Factura;
import gm.inventarios.modelo.MetodoPago;
import gm.inventarios.servicio.FacturacionServicio;

@RestController
@RequestMapping("/api/facturacion")
@CrossOrigin(origins = "*")
public class FacturacionControlador {

    private final FacturacionServicio facturacionServicio;

    public FacturacionControlador(FacturacionServicio facturacionServicio) {
        this.facturacionServicio = facturacionServicio;
    }

    /**
     * 🧾 Genera una factura y la guarda en base de datos.
     * Retorna la información de la factura en formato JSON.
     */
    @PostMapping("/generar/{idPedido}")
    public ResponseEntity<Factura> generarFactura(
            @PathVariable Long idPedido,
            @RequestParam String metodoPago) {
        try {
            Factura factura = facturacionServicio.generarFactura(idPedido, metodoPago);
            return ResponseEntity.ok(factura);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 📄 Genera la factura y devuelve el PDF directamente como descarga.
     */
    @PostMapping("/generarPdf/{idPedido}")
    public ResponseEntity<byte[]> generarFacturaPDF(
            @PathVariable Long idPedido,
            @RequestParam String metodoPago) {
        try {
            byte[] pdfBytes = facturacionServicio.generarFacturaPDF(idPedido, metodoPago);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=Factura_" + idPedido + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

   
    
    
    // devuelve Efectivo, Tarjeta, Nequi
    @GetMapping("/metodos-pago")
    public List<String> metodosPago() {
        // Devuelve: ["EFECTIVO","TARJETA","NEQUI"]
        return Arrays.stream(MetodoPago.values())
                     .map(Enum::name)
                     .toList();
    }
}