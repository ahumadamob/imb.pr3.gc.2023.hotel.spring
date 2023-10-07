package imb.pr3.hotel.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr3.hotel.entity.Cliente;
import imb.pr3.hotel.repository.ClienteRepository;

@Service
@Primary
public class ClienteMysql implements IClienteService{

	@Autowired
	ClienteRepository repo;
	
	@Override
	public List<Cliente> obtenerTodos() {
		return repo.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> ClienteOptional = repo.findById(id);
        if (ClienteOptional.isPresent()) {
            return ClienteOptional.get();
        }
        else {
            return null;
        }
	}

	@Override
	public Cliente crearCliente(Cliente Cliente) {
		return repo.save(Cliente);
	}

	@Override
	public String eliminarCliente(Long id) {
		boolean existeRegistro = repo.existsById(id);
	    if (existeRegistro) {
	        repo.deleteById(id);
	        return "Cliente eliminado correctamente.";
	    } else {
	        return "Registro no encontrado.";
	    }
	}

	@Override
	public Cliente modificarCliente(Cliente ClienteModificado) {
		Long id = ClienteModificado.getId();
	    Optional<Cliente> ClienteOptional = repo.findById(id);
	    if (ClienteOptional.isPresent()) {
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

}