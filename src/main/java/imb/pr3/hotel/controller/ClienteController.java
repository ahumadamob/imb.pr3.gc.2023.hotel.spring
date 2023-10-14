package imb.pr3.hotel.controller;

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
import imb.pr3.hotel.service.jpa.ClienteServiceImpl;
import imb.pr3.hotel.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@ControllerAdvice
@RequestMapping("/api/v1")
public class ClienteController {

	@Autowired
	IClienteService service;
	
	// Endpoint para obtener todos los clientes
	@GetMapping("/Cliente")
	public ResponseEntity<APIResponse<List<Cliente>>> obtenerTodos(){
		List<Cliente> cliente = service.obtenerTodos();
		return cliente.isEmpty()
			   ? ResponseUtil.notFound("No hay clientes registrados.")
	           : ResponseUtil.success(cliente);
	}
	
	// Endpoint para obtener un cliente por ID
	@GetMapping("/Cliente/{id}")
	public ResponseEntity<APIResponse<Cliente>> buscarPorId(@PathVariable("id") Integer id) {
	    Cliente ClientePorId = service.buscarPorId(id);
	    return ClientePorId == null 
	    		? ResponseUtil.notFound("No se encontró el cliente con el identificador proporcionado")
				: ResponseUtil.success(ClientePorId);
	}

	// Endpoint para crear un cliente
	@PostMapping("/Cliente")
	public ResponseEntity<APIResponse<Cliente>> crear(@RequestBody Cliente Cliente) {
		return service.existe(Cliente.getId())
				? ResponseUtil.badRequest("Ya existe un cliente con el identificador proporcionado.")
        		: ResponseUtil.created(service.crear(Cliente)); //Crea el cliente si está el id disponible
	}

	// Endpoint para modificar un cliente
	@PutMapping("/Cliente") //Define el método "modificar", y funciona tomando un objeto Cliente para devolver una respuesta con el ResponseEntity
	public ResponseEntity<APIResponse<Cliente>> modificar(@RequestBody Cliente Cliente) {
		return service.existe(Cliente.getId()) //Verifica existencia del cliente por ID 
				? ResponseUtil.success(service.crear(Cliente)) //Modifica el cliente si encuentra
				: ResponseUtil.badRequest("No existe un cliente con ese identificador."); //Devuelve 400 si no  encuentra
	}

	// Endpoint para eliminar un cliente con el ID
	@DeleteMapping("/Cliente/{id}")
	public ResponseEntity<APIResponse<String>> eliminar(@PathVariable("id") Integer id) {
	    return service.existe(id) 
	    		? ResponseUtil.success(service.eliminar(id))
	    		:ResponseUtil.badRequest("No se encontró ese cliente. No se borró registro alguno.");
	}

	@ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Cliente>> handleException(Exception ex) {    	
    	return ResponseUtil.badRequest(ex.getMessage());
    }
	
	//Manejador de excepciones, modifica mensajes para los errores
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Cliente>> handleConstraintViolationException(ConstraintViolationException ex) {
    	return ResponseUtil.handleConstraintException(ex);
    }
}