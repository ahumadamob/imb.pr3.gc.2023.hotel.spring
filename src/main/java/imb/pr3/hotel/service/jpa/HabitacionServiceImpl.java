package imb.pr3.hotel.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.hotel.entity.Habitacion;

import imb.pr3.hotel.repository.HabitacionlRepository;
import imb.pr3.hotel.service.IHabitacionService;


@Service
public class HabitacionServiceImpl implements IHabitacionService{

	@Autowired
	HabitacionlRepository repo; 

	@Override
	public List<Habitacion> obtenerTodasLasHabitacion() {

		return repo.findAll();
	}

	@Override
	public Habitacion buscarHabitacionPorId(Integer id) {
		Optional<Habitacion> optional = repo.findById(id);

		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}	
	
	@Override
	public Habitacion crearHabitacion(Habitacion habitacion) { //creacion de una nueva habitacion
		return repo.save(habitacion); //implementacion de los metodos de JPA
	}
	
	@Override
	public String eliminarHabitacion(Long id) {  //Borrar una habitacion si existe
		boolean existeRegistro = repo.existsById(id); //implementacion de JPA
	    if (existeRegistro) {
	        repo.deleteById(id); //Método de JPA
	        return "Habitacion eliminada correctamente.";
	    } else {
	        return "Registro no encontrado.";
	    }
	}

	@Override
	public habitacion modificarHabitacion(Habitacion habitacionModificada) { //Modificar una habitacion
		Long id = habitacionModificada.getId();
	    Optional<Habitacion> HabitacionOptional = repo.findById(id); //Método de JPA
	    if (HabitacionOptional.isPresent()) {
	    	//guardo la habitacion temporalmente para intercambiar los datos viejos con los nuevos
	        Habitacion HabitacionExistente = HabitacionOptional.get();
	        HabitacionExistente.setPiso(habitacionModificada.getPiso());
	        HabitacionExistente.setNumero(habitacionModificada.getNumero());
	        HabitacionExistente.setPuntoCardinal(habitacionModificada.getPuntoCardinal());
	        HabitacionExistente.setCapacidad(habitacionModificada.getCapacidad());
	        HabitacionExistente.setCamas(habitacionModificada.getCamas());
	        HabitacionExistente.setSuite(habitacionModificada.getSuite());

	        return repo.save(HabitacionExistente);
	    } else {
	        return null;
	    }
	}
}
