package imb.pr3.hotel.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.hotel.entity.Cliente;
//Se encarga de trabajar con la base de datos, interactúa usando métodos traídos de jpa
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	public List<Cliente>findByGenero(String genero); // Función para buscar según género
}