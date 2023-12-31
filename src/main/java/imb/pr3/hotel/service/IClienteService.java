package imb.pr3.hotel.service;

import java.util.List;
import imb.pr3.hotel.entity.Cliente;

//Esta interfaz permitirá implementar estos métodos en otras clases
public interface IClienteService {
	public List<Cliente> obtenerTodos(); //Recuperar TODOS los clientes de la bbdd
    public Cliente buscarPorId(Integer id); //Recuperar un cliente, por su ID
    //public Cliente crear(Cliente Cliente); //Guardar nuevo cliente en bbdd
    //public Cliente modificar(Cliente Cliente); //Cambiar los datos del cliente en la bbdd
    public Cliente guardar(Cliente Cliente); //Creación y modificación
    public String eliminar(Integer id); //Eliminar un cliente de la bbdd por ID
    public boolean existe(Integer id); //Evaluar la existencia del ID
}