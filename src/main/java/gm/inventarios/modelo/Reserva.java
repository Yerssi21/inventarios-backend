package gm.inventarios.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idReserva;

	String nombreCliente;
	LocalDateTime fechaHora;
	Integer numeroPersonas;
}
