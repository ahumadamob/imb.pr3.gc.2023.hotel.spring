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

import imb.pr3.hotel.entity.Habitacion;
import imb.pr3.hotel.service.iHabitacionService;



@RestController
@RequestMapping("/api/v1")
public class HabitacionController {

	@Autowired
	iHabitacionService service;


	@GetMapping("/habitacion")
	public ResponseEntity<APIResponse<List<Habitacion>>>buscarTodos() {

		List<Habitacion> habitacion = service.buscarTodas();
		if(habitacion.isEmpty()) {
			APIResponse<List<Habitacion>> response = new APIResponse<List<Habitacion>> (200, null, service.buscarTodas());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			APIResponse<List<Habitacion>> response = new APIResponse<List<Habitacion>> (200, null, habitacion);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@GetMapping("/habitacion/{id}")
	public ResponseEntity<APIResponse<Habitacion>> buscarPorId(@PathVariable("id") Integer id){
		Habitacion habitacion = service.buscarPorId(id);
		if(habitacion == null) {
			List <String> messages = new ArrayList<>();
			messages.add("No se encontró la habitacion con el id: " + id.toString());
			messages.add("Revise nuevamente el parámetro.");
			APIResponse<Habitacion> response = new APIResponse<Habitacion>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		else {
			APIResponse<Habitacion> response = new APIResponse<Habitacion>(HttpStatus.OK.value(), null, habitacion);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@PostMapping("/habitacion")
	public ResponseEntity<APIResponse<Habitacion>> crearcomentario(@RequestBody Habitacion habitacion) {

		if(habitacion.getId() != null) {
			Habitacion buscahabitacion = service.buscarPorId(habitacion.getId());

			if(buscahabitacion!=null) {
				List <String> messages = new ArrayList<>();
				messages.add("Ya existe una habitacion con el id: " + habitacion.getId().toString());
				messages.add("Para actualizar utilice el verbo PUT");
				APIResponse<Habitacion> response = new APIResponse<Habitacion>(HttpStatus.BAD_REQUEST.value(), messages, null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}

		service.guardar(habitacion);
		APIResponse<Habitacion> response = new APIResponse<Habitacion>(HttpStatus.CREATED.value(), null, habitacion);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PutMapping("/habitacion")
	public ResponseEntity<APIResponse<Habitacion>> actualizarcomentario(@RequestBody Habitacion habitacion) {

		boolean isError;
		String idStr;

		if(habitacion.getId() != null) {
			Habitacion buscahabitacion = service.buscarPorId(habitacion.getId());
			idStr = habitacion.getId().toString();

			if(buscahabitacion!=null) {
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
			messages.add("No existe una habitacion con el id: " + idStr);
			messages.add("Para crear una nueva habitacion, utilice el verbo POST");
			APIResponse<Habitacion> response = new APIResponse<Habitacion>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

		else {
			service.guardar(habitacion);
			APIResponse<Habitacion> response = new APIResponse<Habitacion>(HttpStatus.OK.value(), null, habitacion);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@DeleteMapping("/habitacion/{id}")
	public ResponseEntity<APIResponse<Habitacion>> eliminar(@PathVariable("id") Integer id){
		Habitacion buscahabitacion = service.buscarPorId(id);

		if(buscahabitacion == null) {
			List <String> messages = new ArrayList<>();
			messages.add("No existe un comentario con el id: " + id.toString());
			APIResponse<Habitacion> response = new APIResponse<Habitacion>(HttpStatus.OK.value(), null, buscahabitacion);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

		else {
			service.eliminar(id);
			List <String> messages = new ArrayList<>();
			messages.add("La habitacion con id " + id.toString() + " ha sido eliminado con éxito");
			APIResponse<Habitacion> response = new APIResponse<Habitacion>(HttpStatus.OK.value(), messages, buscahabitacion);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
