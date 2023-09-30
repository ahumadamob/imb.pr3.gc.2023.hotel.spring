package imb.pr3.hotel.service;

import java.util.List;
import imb.pr3.hotel.entity.Cliente;

//Esta interfaz permitirá implementar estos métodos en otras clases
public interface IClienteService {
	public List<Cliente> obtenerTodosLosClientes(); //Recuperar TODOS los clientes de la bbdd
    public Cliente buscarClientePorId(Long id); //Recuperar un cliente, por su ID
    public Cliente crearCliente(Cliente Cliente); //Guardar nuevo cliente en bbdd
    public String eliminarCliente(Long id); //Eliminar un cliente de la bbdd por ID
    public Cliente modificarCliente(Cliente Cliente); //Cambiar los datos del cliente en la bbdd
}