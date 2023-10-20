package imb.pr3.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.hotel.entity.Proveedor;
import imb.pr3.hotel.service.iProveedorService;



@RestController
@RequestMapping("/api/v1")
public class ProveedorController {

	@Autowired
	iProveedorService service;


	@GetMapping("/proveedor")
	public ResponseEntity<APIResponse<List<Proveedor>>>obtenertodos() {

		List<Proveedor> proveedor = service.obtenertodos();
		if(proveedor.isEmpty()) {
			APIResponse<List<Proveedor>> response = new APIResponse<List<Proveedor>> (200, null, service.obtenertodos());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			APIResponse<List<Proveedor>> response = new APIResponse<List<Proveedor>> (200, null, proveedor);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@GetMapping("/Proveedor/{id}")
	public ResponseEntity<APIResponse<Proveedor>> buscarPorId(@PathVariable("id") Integer id){
		Proveedor proveedor = service.buscarPorId(id);
		if(proveedor == null) {
			List <String> messages = new ArrayList<>();
			messages.add("No se encontró el proveedor con el id: " + id.toString());
			messages.add("Revise nuevamente el parámetro.");
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		else {
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), null, proveedor);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@PostMapping("/proveedor")
	public ResponseEntity<APIResponse<Proveedor>> crearcomentario(@RequestBody Proveedor proveedor) {

		if(proveedor.getId() != null) {
			Proveedor buscaproveedor = service.buscarPorId(proveedor.getId());

			if(buscaproveedor!=null) {
				List <String> messages = new ArrayList<>();
				messages.add("Ya existe un proveedor  con el id: " + proveedor.getId().toString());
				messages.add("Para actualizar utilice el verbo PUT");
				APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}

		service.guardar(proveedor);
		APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.CREATED.value(), null, proveedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PutMapping("/proveedor")
	public ResponseEntity<APIResponse<Proveedor>> actualizarcomentario(@RequestBody Proveedor proveedor) {

		boolean isError;
		String idStr;

		if(proveedor.getId() != null) {
			Proveedor buscaproveedor = service.buscarPorId(proveedor.getId());
			idStr = proveedor.getId().toString();

			if(buscaproveedor!=null) {
				isError = false;
			}
			else {
				isError = true;
			}
		}
		else{
			isError = true;
			idStr = "No definido";
		}

		if(isError) {

			List <String> messages = new ArrayList<>();
			messages.add("No existe un proveedor con el id: " + idStr);
			messages.add("Para crear un nuevo proveedor , utilice el verbo POST");
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

		else {
			service.guardar(proveedor);
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), null, proveedor);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@DeleteMapping("/proveedor/{id}")
	public ResponseEntity<APIResponse<Proveedor>> eliminar(@PathVariable("id") Integer id){
		Proveedor buscaproveedor = service.buscarPorId(id);

		if(buscaproveedor == null) {
			List <String> messages = new ArrayList<>();
			messages.add("No existe un comentario con el id: " + id.toString());
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), null, buscaproveedor);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

		else {
			service.eliminar(id);
			List <String> messages = new ArrayList<>();
			messages.add("El proveedor  con id " + id.toString() + " ha sido eliminado con éxito");
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), messages, buscaproveedor);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
