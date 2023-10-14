package imb.pr3.hotel.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr3.hotel.entity.Cliente;
import imb.pr3.hotel.repository.ClienteRepository;
import imb.pr3.hotel.service.IClienteService;

//Este archivo se encarga de manejar las operaciones CRUD para el cliente, implementando IClienteService
@Service
@Primary
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	ClienteRepository repo; //Objeto para interactuar con la base de datos
	
	@Override
	public List<Cliente> obtenerTodos() { //Recuperar una LISTA con lso clientes
		return repo.findAll(); //Método de JPA
	}

	@Override
	public Cliente buscarPorId(Integer id) { //Recupera un cliente por ID
		Optional<Cliente> ClienteOptional = repo.findById(id); //Método de JPA
        if (ClienteOptional.isPresent()) {
            return ClienteOptional.get();
        }
        else {
            return null;
        }
	}

	@Override
	public Cliente crear(Cliente Cliente) { //Crear un cliente nuevo
		return repo.save(Cliente); //Método de JPA
	}

	@Override
	public String eliminar(Integer id) {  //Borrar un cliente si existe
		boolean existeRegistro = repo.existsById(id); //Método de JPA
	    if (existeRegistro) {
	        repo.deleteById(id); //Método de JPA
	        return "Cliente eliminado correctamente.";
	    } else {
	        return "Registro no encontrado.";
	    }
	}

	@Override
	public Cliente modificar(Cliente ClienteModificado) { //Modificar un cliente
		Integer id = ClienteModificado.getId();
	    Optional<Cliente> ClienteOptional = repo.findById(id); //Método de JPA
	    if (ClienteOptional.isPresent()) {
	    	//Creo un cliente temporal para intercambiar los nuevos datos con los antiguos
	        Cliente ClienteExistente = ClienteOptional.get();
	        ClienteExistente.setNombre(ClienteModificado.getNombre());
	        ClienteExistente.setApellido(ClienteModificado.getApellido());
	        ClienteExistente.setFechaNacimiento(ClienteModificado.getFechaNacimiento());
	        ClienteExistente.setDomicilio(ClienteModificado.getDomicilio());
	        ClienteExistente.setTelefono(ClienteModificado.getTelefono());
	        ClienteExistente.setCorreo(ClienteModificado.getCorreo());

	        return repo.save(ClienteExistente);
	    } else {
	        return null;
	    }
	}
	
	@Override
	public boolean existe(Integer id) {
		return(id ==null)? false:repo.existsById(id);
	}

}