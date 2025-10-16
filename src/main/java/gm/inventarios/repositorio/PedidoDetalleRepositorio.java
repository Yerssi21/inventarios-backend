package gm.inventarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import gm.inventarios.modelo.PedidoDetalle;

public interface PedidoDetalleRepositorio extends JpaRepository<PedidoDetalle, Long>{

} 

