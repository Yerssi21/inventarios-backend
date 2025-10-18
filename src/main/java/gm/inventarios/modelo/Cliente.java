package gm.inventarios.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 20)
    private String tipoDocumento; // "CC", "TI", "CE", etc.

    @Column(unique = true, length = 20)
    private String numeroDocumento;

    @Column(length = 100)
    private String correo;

    @Column(length = 20)
    private String telefono;

    @Column(length = 150)
    private String direccion;

    @Column(length = 200)
    private String observaciones;
}
