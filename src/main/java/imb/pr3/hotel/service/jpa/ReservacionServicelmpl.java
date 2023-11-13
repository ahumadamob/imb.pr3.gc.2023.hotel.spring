package imb.pr3.hotel.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import imb.pr3.hotel.entity.Reservacion;
import imb.pr3.hotel.repository.ReservacionRepository;
import imb.pr3.hotel.service.iReservacionService;

public class ReservacionServicelmpl implements iReservacionService{
	
	@Autowired
	ReservacionRepository repo; 

	@Override
	public List<Reservacion> buscarTodas() {

		return repo.findAll();
	}

	@Override
	public Reservacion buscarPorId(Integer id) {
		Optional<Reservacion> optional = repo.findById(id);

		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}

	}	
	
	@Override
	public void guardar(Reservacion reservacion) {

		repo.save(reservacion);
	}

	@Override
	public void eliminar(Integer id) {

		repo.deleteById(id);

	}

}
