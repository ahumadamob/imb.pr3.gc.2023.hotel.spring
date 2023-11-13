package imb.pr3.hotel.controller;

import java.util.ArrayList;
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

    @GetMapping("/Habitacion")
    public ResponseEntity<APIResponse<List<Habitacion>>> obtenerTodos(){
        List<Habitacion> habitacion = service.obtenerTodos();
        return habitacion.isEmpty()
               ? ResponseUtil.notFound("No hay habitaciones registradas.")
               : ResponseUtil.success(habitacion);
    }

    @GetMapping("/Habitacion/{id}")
    public ResponseEntity<APIResponse<Habitacion>> buscarPorId(@PathVariable("id") Integer id) {
        Habitacion habitacionPorId = service.buscarPorId(id);
        return habitacionPorId == null
                ? ResponseUtil.notFound("No se encontró la habitacion con el identificador proporcionado")
                : ResponseUtil.success(habitacionPorId);
    }

    @PostMapping("/Habitacion")
    public ResponseEntity<APIResponse<Habitacion>> crear(@RequestBody Habitacion habitacion) {
        return service.existe(habitacion.getId())
                ? ResponseUtil.badRequest("Ya existe una habitacion con el identificador proporcionado.")
                : ResponseUtil.created(service.guardar(habitacion));
    }

    @PutMapping("/Habitacion")
    public ResponseEntity<APIResponse<Habitacion>> modificar(@RequestBody Habitacion habitacion) {
        return service.existe(habitacion.getId())
                ? ResponseUtil.success(service.guardar(habitacion))
                : ResponseUtil.badRequest("No existe una habitacion con ese identificador.");
    }

    @DeleteMapping("/Habitacion/{id}")
    public ResponseEntity<APIResponse<String>> eliminar(@PathVariable("id") Integer id) {
        return service.existe(id)
                ? ResponseUtil.success(service.eliminar(id))
                : ResponseUtil.badRequest("No se encontró esa habitacion. No se borró registro alguno.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Habitacion>> handleException(Exception ex) {
        return ResponseUtil.badRequest(ex.getMessage());
    }

    /*
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Habitacion>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
    */
}
