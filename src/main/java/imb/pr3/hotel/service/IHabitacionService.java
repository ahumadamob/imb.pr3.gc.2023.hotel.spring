package imb.pr3.hotel.service;

import java.util.List;

import imb.pr3.hotel.entity.Habitacion;


public interface IHabitacionService {

	public List<Habitacion> obtenerTodasLasHabitacion(); //Obtiente todas las habitaciones de la bbdd
    public Habitacion buscarHabitacionPorId(Long id); //Obtiene una habitacion por su ID
    public Habitacion crearHabitacion(Habitacion habitacion); //Crea una neva habitacion en la bbdd
    public String eliminarHabitacion(Long id); //Eliminar una habitacion por ID
    public Habitacion modificarHabitacion(Habitacion habitacion); //Modifca los datos de la habitacion en la bbdd
}
