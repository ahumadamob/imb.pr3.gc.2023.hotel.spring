package imb.pr3.hotel.service;

import java.util.List;

import imb.pr3.hotel.entity.Habitacion;


public interface iHabitacionService {

	List <Habitacion> buscarTodas();
	Habitacion buscarPorId(Integer id);
	void guardar(Habitacion habitacion);
	void eliminar (Integer id);
}
