package imb.pr3.hotel.service;

import java.util.List;

import imb.pr3.hotel.entity.Habitacion;


public interface IHabitacionService {
    public List<Habitacion> obtenerTodos(); // Recuperar TODAS las habitaciones de la bbdd
    public Habitacion buscarPorId(Integer id); // Recuperar una habitación, por su ID
    public Habitacion guardar(Habitacion Habitacion); // Creación y modificación
    public String eliminar(Integer id); // Eliminar una habitación de la bbdd por ID
    public boolean existe(Integer id); // Evaluar la existencia del ID
}

