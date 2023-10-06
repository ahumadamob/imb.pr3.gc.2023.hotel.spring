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
import imb.pr3.hotel.util.ResponseUtil;
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
		List<Cliente> clientes = response.getData(); //Leer la respuesta y retornar OK o 404 dependiendo de lo encontrado
		return (clientes.isEmpty())
		        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
		        : ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	// Endpoint para obtener un cliente por ID
	@GetMapping("/Cliente/{id}")
	public ResponseEntity<APIResponse<Cliente>> buscarClientePorId(@PathVariable("id") Long id) {
	    Cliente ClientePorId = service.buscarClientePorId(id);
	    return ClientePorId == null 
	    		? ResponseUtil.notFound("No se encontró el cliente con el identificador proporcionado")
				: ResponseUtil.success(ClientePorId);
	}

	// Endpoint para crear un cliente
	@PostMapping("/Cliente")
	public ResponseEntity<APIResponse<Cliente>> crearCliente(@RequestBody Cliente Cliente) {
		return this.existe(Cliente.getId())
				? ResponseUtil.badRequest("Ya existe un cliente con el identificador proporcionado.")
        		: ResponseUtil.created(service.crearCliente(Cliente)); //Crea el cliente si está el id disponible
	}

	// Endpoint para modificar un cliente
	@PutMapping("/Cliente")
	public ResponseEntity<APIResponse<Cliente>> modificarCliente(@RequestBody Cliente Cliente) {
		return this.existe(Cliente.getId()) //Verificar existencia del cliente por ID 
				? ResponseUtil.success(service.crearCliente(Cliente)) //Modifica el cliente si encuentra
				: ResponseUtil.badRequest("No existe un cliente con ese identificador."); //400 si no  encuentra
	}

	// Endpoint para eliminar un cliente con el ID
	@DeleteMapping("/Cliente/{id}")
	public ResponseEntity<APIResponse<String>> eliminarCliente(@PathVariable("id") Long id) {
	    return this.existe(id) 
	    		? ResponseUtil.success(service.eliminarCliente(id))
	    		:ResponseUtil.badRequest("No se encontró ese cliente. No se borró registro alguno.");
	}

	//Verificador de existencia usando el ID
	public boolean existe(Long id) {
	    return (id != null) ? (service.buscarClientePorId(id) != null) : false;
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