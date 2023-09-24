package imb.pr3.hotel.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.hotel.entity.Habitacion;
import imb.pr3.hotel.repository.HabitacionRepository;
import imb.pr3.hotel.service.iHabitacionService;

@Service
public class HabitacionServiceImpl implements iHabitacionService{

	@Autowired
	HabitacionRepository repo; 

	@Override
	public List<Habitacion> buscarTodas() {

		return repo.findAll();
	}

	@Override
	public Habitacion buscarPorId(Integer id) {
		Optional<Habitacion> optional = repo.findById(id);

		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}

	}	
	
	@Override
	public void guardar(Habitacion habitacion) {

		repo.save(habitacion);
	}

	@Override
	public void eliminar(Integer id) {

		repo.deleteById(id);

	}
}
