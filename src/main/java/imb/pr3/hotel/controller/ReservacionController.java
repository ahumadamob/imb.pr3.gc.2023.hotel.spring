package imb.pr3.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.hotel.entity.Reservacion;
import imb.pr3.hotel.service.iReservacionService;



@RestController
@ControllerAdvice
@RequestMapping("/api/v1")
public class ReservacionController {

	@Autowired
	iReservacionService service;


	@GetMapping("/reservacion")
	public ResponseEntity<APIResponse<List<Reservacion>>>buscarTodos() {

		List<Reservacion> habitacion = service.buscarTodas();
		if(habitacion.isEmpty()) {
			APIResponse<List<Reservacion>> response = new APIResponse<List<Reservacion>> (200, null, service.buscarTodas());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			APIResponse<List<Reservacion>> response = new APIResponse<List<Reservacion>> (200, null, habitacion);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@GetMapping("/reservacion/{id}")
	public ResponseEntity<APIResponse<Reservacion>> buscarPorId(@PathVariable("id") Integer id){
		Reservacion Reservacion = service.buscarPorId(id);
		Reservacion reservacion = null;
		if(reservacion == null) {
			List <String> messages = new ArrayList<>();
			messages.add("No se encontró la reservacion con el id: " + id.toString());
			messages.add("Revise nuevamente el parámetro.");
			APIResponse<Reservacion> response = new APIResponse<Reservacion>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		else {
			APIResponse<Reservacion> response = new APIResponse<Reservacion>(HttpStatus.OK.value(), null, reservacion);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@PostMapping("/reservacion")
	public ResponseEntity<APIResponse<Reservacion>> crearcomentario(@RequestBody Reservacion habitacion) {

		Reservacion reservacion = null;
		if(reservacion.getId() != null) {
			Reservacion buscareservacion= service.buscarPorId(habitacion.getId());

			if(buscareservacion!=null) {
				List <String> messages = new ArrayList<>();
				messages.add("Ya existe una reservacion con el id: " + habitacion.getId().toString());
				messages.add("Para actualizar utilice el verbo PUT");
				APIResponse<Reservacion> response = new APIResponse<Reservacion>(HttpStatus.BAD_REQUEST.value(), messages, null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}

		service.guardar(habitacion);
		APIResponse<Reservacion> response = new APIResponse<Reservacion>(HttpStatus.CREATED.value(), null, habitacion);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PutMapping("/habitacion")
	public ResponseEntity<APIResponse<Reservacion>> actualizarcomentario(@RequestBody Reservacion reservacion) {

		boolean isError;
		String idStr;

		if(reservacion.getId() != null) {
			Reservacion buscareservacion = service.buscarPorId(reservacion.getId());
			idStr = reservacion.getId().toString();

			if(buscareservacion!=null) {
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
			messages.add("No existe una reservacion con el id: " + idStr);
			messages.add("Para crear una nueva habitacion, utilice el verbo POST");
			APIResponse<Reservacion> response = new APIResponse<Reservacion>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

		else {
			service.guardar(reservacion);
			APIResponse<Reservacion> response = new APIResponse<Reservacion>(HttpStatus.OK.value(), null, reservacion);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@DeleteMapping("/reservacion/{id}")
	public ResponseEntity<APIResponse<Reservacion>> eliminar(@PathVariable("id") Integer id){
		Reservacion buscahabitacion = service.buscarPorId(id);

		Reservacion buscareservacion = null;
		List <String> messages = new ArrayList<>();
		messages.add("No existe un comentario con el id: " + id.toString());
		APIResponse<Reservacion> response = new APIResponse<Reservacion>(HttpStatus.OK.value(), null, buscareservacion);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
