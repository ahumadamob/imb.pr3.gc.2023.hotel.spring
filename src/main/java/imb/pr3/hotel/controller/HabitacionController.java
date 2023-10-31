package imb.pr3.hotel.controller;

0import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
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

import imb.pr3.hotel.entity.Habitacion;
import imb.pr3.hotel.service.IHabitacionService;




@RestController
@ControllerAdvice
@RequestMapping("/api/v1")
public class HabitacionController {

    @Autowired
    IHabitacionService service;
    
    // Endpoint para obtener todas las habitaciones
    @GetMapping("/Habitacion")
    public ResponseEntity<APIResponse<List<Habitacion>>> obtenerTodasLasHabitaciones(){
        APIResponse<List<Habitacion>> response = new APIResponse<List<Habitacion>>(200, null, service.obtenerTodasLasHabitacion());    
        List<Habitacion> habitaciones = response.getData(); 
        return (habitaciones.isEmpty())
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
                : ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    // Endpoint para obtener una habitación por ID
    @GetMapping("/Habitacion/{id}")
    public ResponseEntity<APIResponse<Habitacion>> buscarHabitacionPorId(@PathVariable("id") Long id) {
        Habitacion habitacionPorId = service.buscarHabitacionPorId(id);
        return habitacionPorId == null 
                ? ResponseUtil.notFound("No se encontró la habitación con el identificador proporcionado")
                : ResponseUtil.success(habitacionPorId);
    }

    // Endpoint para crear una habitación
    @PostMapping("/Habitacion")
    public ResponseEntity<APIResponse<Habitacion>> crearHabitacion(@RequestBody Habitacion habitacion) {
        return this.existe(habitacion.getId())
                ? ResponseUtil.badRequest("Ya existe una habitación con el identificador proporcionado.")
                : ResponseUtil.created(service.crearHabitacion(habitacion));
    }

    // Endpoint para modificar una habitación
    @PutMapping("/Habitacion")
    public ResponseEntity<APIResponse<Habitacion>> modificarHabitacion(@RequestBody Habitacion habitacion) {
        return this.existe(habitacion.getId()) 
                ? ResponseUtil.success(service.crearHabitacion(habitacion))
                : ResponseUtil.badRequest("No existe una habitación con ese identificador.");
    }

    // Endpoint para eliminar una habitación con el ID
    @DeleteMapping("/Habitacion/{id}")
    public ResponseEntity<APIResponse<String>> eliminarHabitacion(@PathVariable("id") Long id) {
        return this.existe(id) 
                ? ResponseUtil.success(service.eliminarHabitacion(id))
                : ResponseUtil.badRequest("No se encontró esa habitación. No se borró registro alguno.");
    }

    // Verificador de existencia usando el ID
    public boolean existe(Long id) {
        return (id != null) ? (service.buscarHabitacionPorId(id) != null) : false;
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Habitacion>> handleException(Exception ex) {        
        return ResponseUtil.badRequest(ex.getMessage());
    }
    
    // Manejador de excepciones, modifica mensajes para los errores
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Habitacion>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
}
