package gm.inventarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import gm.inventarios.modelo.Reserva;

public interface ReservaRepositorio extends JpaRepository<Reserva, Long>{

}
