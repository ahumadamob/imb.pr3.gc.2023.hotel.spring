package imb.pr3.hotel.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.hotel.entity.Habitacion;

import imb.pr3.hotel.repository.HabitacionRepository;
import imb.pr3.hotel.service.IHabitacionService;


@Service
public class HabitacionServiceImpl implements IHabitacionService {

    @Autowired
    HabitacionRepository repo; // Objeto para interactuar con la base de datos

    @Override
    public List<Habitacion> obtenerTodos() { // Recuperar una LISTA con las habitaciones
        return repo.findAll(); // Método de JPA
    }

    @Override
    public Habitacion buscarPorId(Integer id) { // Recupera una habitación por ID
        Optional<Habitacion> habitacionOptional = repo.findById(id); // Método de JPA
        if (habitacionOptional.isPresent()) {
            return habitacionOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public String eliminar(Integer id) { // Borrar una habitación si existe
        boolean existeRegistro = repo.existsById(id); // Método de JPA
        if (existeRegistro) {
            repo.deleteById(id); // Método de JPA
            return "Habitación eliminada correctamente.";
        } else {
            return "Registro no encontrado.";
        }
    }

    @Override
    public Habitacion guardar(Habitacion habitacion) {
        return repo.save(habitacion); // Método de JPA
    }

    @Override
    public boolean existe(Integer id) {
        return (id == null) ? false : repo.existsById(id);
    }
}
