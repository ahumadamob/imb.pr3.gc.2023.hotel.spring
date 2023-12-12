package imb.pr3.hotel.service;

import java.util.List;
import imb.pr3.hotel.entity.Cliente;

//Esta interfaz permitirá implementar estos métodos en otras clases
public interface IClienteService {
	public List<Cliente> obtenerTodos(); //Recuperar TODOS los clientes de la bbdd
    public Cliente buscarPorId(Integer id); //Recuperar un cliente, por su ID
    public Cliente guardar(Cliente Cliente); //Creación y modificación
    public String eliminar(Integer id); //Eliminar un cliente de la bbdd por ID
    public boolean existe(Integer id); //Evaluar la existencia del ID
    public List<Cliente> obtenerHabilitados(boolean habilitado);
    public boolean habilitar(Integer id);
}