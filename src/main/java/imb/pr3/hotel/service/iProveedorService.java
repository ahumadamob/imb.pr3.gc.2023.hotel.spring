package imb.pr3.hotel.service;

import java.util.List;

import imb.pr3.hotel.entity.Proveedor;


public interface iProveedorService {

	List <Proveedor> obtenertodos();
	Proveedor buscarPorId(Integer id);	
	void guardar(Proveedor proveedor);
	void eliminar (Integer id);
	List<Proveedor> buscartodas();
}





