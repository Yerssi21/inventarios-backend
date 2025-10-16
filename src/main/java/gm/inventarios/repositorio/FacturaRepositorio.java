package gm.inventarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import gm.inventarios.modelo.Factura;

public interface FacturaRepositorio extends JpaRepository<Factura, Long> {

}
