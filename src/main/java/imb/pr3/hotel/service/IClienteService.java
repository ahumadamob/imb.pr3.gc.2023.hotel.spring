package imb.pr3.hotel.services;

import java.util.List;
import imb.pr3.hotel.entity.Cliente;

public interface IClienteService {
	List<Cliente> obtenerTodos();
    Cliente buscarPorId(Long id);
    Cliente crearCliente(Cliente Cliente);
    String eliminarCliente(Long id);
    Cliente modificarCliente(Cliente Cliente);
}