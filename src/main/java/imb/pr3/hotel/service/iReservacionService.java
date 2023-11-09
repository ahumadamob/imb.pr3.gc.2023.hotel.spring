package imb.pr3.hotel.service;

import java.util.List;

import imb.pr3.hotel.entity.Reservacion;

public interface iReservacionService {
	
	List <Reservacion> buscarTodas();
	Reservacion buscarPorId(Integer id);
	void guardar(Reservacion reservacion);
	void eliminar (Integer id);

	
	

}
