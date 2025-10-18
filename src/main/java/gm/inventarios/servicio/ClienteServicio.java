package gm.inventarios.servicio;

import gm.inventarios.modelo.Cliente;
import gm.inventarios.repositorio.ClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

	private final ClienteRepositorio clienteRepositorio;

	public ClienteServicio(ClienteRepositorio clienteRepositorio) {
		this.clienteRepositorio = clienteRepositorio;
	}

	public List<Cliente> listarClientes() {
		return clienteRepositorio.findAll();
	}

	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepositorio.findById(id);
	}

	public Cliente guardarCliente(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}

	public void eliminarCliente(Long id) {
		clienteRepositorio.deleteById(id);
	}
}