package imb.pr3.hotel.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.hotel.entity.Proveedor;
import imb.pr3.hotel.repository.ProveedorRepository;
import imb.pr3.hotel.service.iProveedorService;

@Service
public class ProveedorServicelmpl implements iProveedorService{

	@Autowired
	ProveedorRepository repo; 

	@Override
	public List<Proveedor> buscartodas() {

		return repo.findAll();
	}

	@Override
	public Proveedor buscarPorId(Integer id) {
		Optional<Proveedor> optional = repo.findById(id);

		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}

	}	
	
	@Override
	public void guardar(Proveedor proveedor) {

		repo.save(proveedor);
	}

	@Override
	public void eliminar(Integer id) {

		repo.deleteById(id);

	}

	@Override
	public List<Proveedor> obtenertodos() {
		// TODO Auto-generated method stub
		return null;
	}
}
