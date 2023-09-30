package imb.pr3.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.hotel.entity.Cliente;
import imb.pr3.hotel.service.IClienteService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@ControllerAdvice
@RequestMapping("/api/v1")
public class ClienteController {

	@Autowired
	IClienteService service;
	
	// Endpoint para obtener todos los clientes
	@GetMapping("/Cliente")
	public ResponseEntity<APIResponse<List<Cliente>>>obtenerTodosLosClientes(){
		APIResponse<List<Cliente>> response = new APIResponse<List<Cliente>>(200, null, service.obtenerTodosLosClientes());	
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	// Endpoint para obtener un cliente por ID
	@GetMapping("/Cliente/{id}")
	public ResponseEntity<APIResponse<Cliente>> buscarClientePorId(@PathVariable("id") Long id) {
	    Cliente ClientePorId = service.buscarClientePorId(id);
	    if (this.existe(id)) {
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.OK.value(), null, ClientePorId);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } else {
	        List<String> messages = new ArrayList<>();
	        messages.add("No se encontró un Cliente con ID: " + id.toString());
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), messages, ClientePorId);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}

	// Endpoint para cerar un cliente
	@PostMapping("/Cliente")
	public ResponseEntity<APIResponse<Cliente>> crearCliente(@RequestBody Cliente Cliente) {
	    try {
	    	if (this.existe(Cliente.getId())) {
		    	//Respuesta si hay un error
		        List<String> messages = new ArrayList<>();
		        messages.add("Ya existe un Cliente con ID: " + Cliente.getId());
		        messages.add("Si desea actualizar, use el verbo PUT.");
		        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), messages, Cliente);
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		    } else {
		    	//Respuesta exitosa
		        service.crearCliente(Cliente);
		        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.CREATED.value(), null, Cliente);
		        return ResponseEntity.status(HttpStatus.CREATED).body(response);
		    }
	    }catch(Exception e){
	    	List<String> messages = new ArrayList<>();
	        messages.add("Error desconocido.");
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), messages, Cliente);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
		
	}

	// Endpoint para modificar un cliente
	@PutMapping("/Cliente")
	public ResponseEntity<APIResponse<Cliente>> modificarCliente(@RequestBody Cliente Cliente) {
	    if (this.existe(Cliente.getId())) {
	    	//Checkea la existencia del cliente y lo modifica exitosamente
	        service.modificarCliente(Cliente);
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.OK.value(), null, Cliente);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } else {
	    	//Error por no encontrar al cliente
	        List<String> messages = new ArrayList<>();
	        messages.add("No existe un Cliente con ID: " + Cliente.getId());
	        messages.add("Si desea crear uno, use el verbo POST.");
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), messages, null);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}

	// Endpoint para eliminar un cliente con el ID
	@DeleteMapping("/Cliente/{id}")
	public ResponseEntity<APIResponse<Cliente>> eliminar(@PathVariable("id") Long id) {
	    Cliente ClientePorId = service.buscarClientePorId(id);
	    if (ClientePorId == null) {
	    	//No existe el cliente, manejar error
	        List<String> messages = new ArrayList<>();
	        messages.add("No existe un Cliente con ID: " + id.toString());
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), messages, null);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    } else {
	    	//Existe el cliente, elimina exitosamente
	        service.eliminarCliente(id);
	        List<String> messages = new ArrayList<>();
	        messages.add("Ya no existe un Cliente con ID: " + id.toString() + ". Fue exitosamente eliminado.");
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.OK.value(), messages, null);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    }
	}

	//Verificador de existencia usando el ID
	public boolean existe(Long id) {
	    return (id != null) ? (service.buscarClientePorId(id) != null) : false;
	}
	
	//Manejador de excepciones, modifica mensajes para los errores
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
	    List<String> errors = new ArrayList<>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getMessage());
	    }
	    APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), errors, null);
	    return ResponseEntity.badRequest().body(response);
	}
}