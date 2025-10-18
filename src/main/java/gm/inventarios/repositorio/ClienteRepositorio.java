package gm.inventarios.repositorio;

import gm.inventarios.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    Cliente findByNumeroDocumento(String numeroDocumento);
}
