package imb.pr3.controller;

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

import imb.pr3.entity.Cliente;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import imb.pr3.services.IClienteService;

@RestController
@ControllerAdvice
@RequestMapping("/api/v1")
public class ClienteController {

	@Autowired
	IClienteService service;
	
	@GetMapping("/Cliente")
	public ResponseEntity<APIResponse<List<Cliente>>>obtenerTodos(){
		APIResponse<List<Cliente>> response = new APIResponse<List<Cliente>>(200, null, service.obtenerTodos());	
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/Cliente/{id}")
	public ResponseEntity<APIResponse<Cliente>> buscarporId(@PathVariable("id") Long id) {
	    Cliente ClientePorId = service.buscarPorId(id);
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

	@PostMapping("/Cliente")
	public ResponseEntity<APIResponse<Cliente>> crearCliente(@RequestBody Cliente Cliente) {
	    if (this.existe(Cliente.getId())) {
	        List<String> messages = new ArrayList<>();
	        messages.add("Ya existe un Cliente con ID: " + Cliente.getId());
	        messages.add("Si desea actualizar, use el verbo PUT.");
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), messages, Cliente);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    } else {
	        service.crearCliente(Cliente);
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.CREATED.value(), null, Cliente);
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }
	}

	@PutMapping("/Cliente")
	public ResponseEntity<APIResponse<Cliente>> modificarCliente(@RequestBody Cliente Cliente) {
	    if (this.existe(Cliente.getId())) {
	        service.modificarCliente(Cliente);
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.OK.value(), null, Cliente);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } else {
	        List<String> messages = new ArrayList<>();
	        messages.add("No existe un Cliente con ID: " + Cliente.getId());
	        messages.add("Si desea crear uno, use el verbo POST.");
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), messages, null);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}

	@DeleteMapping("/Cliente/{id}")
	public ResponseEntity<APIResponse<Cliente>> eliminar(@PathVariable("id") Long id) {
	    Cliente ClientePorId = service.buscarPorId(id);
	    if (ClientePorId == null) {
	        List<String> messages = new ArrayList<>();
	        messages.add("No existe un Cliente con ID: " + id.toString());
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.BAD_REQUEST.value(), messages, null);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    } else {
	        service.eliminarCliente(id);
	        List<String> messages = new ArrayList<>();
	        messages.add("Ya no existe un Cliente con ID: " + id.toString() + ". Fue exitosamente eliminado.");
	        APIResponse<Cliente> response = new APIResponse<Cliente>(HttpStatus.OK.value(), messages, null);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    }
	}

	public boolean existe(Long id) {
	    if (id == null) {
	        return false;
	    }
	    Cliente Cliente = service.buscarPorId(id);
	    return Cliente != null;
	}

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