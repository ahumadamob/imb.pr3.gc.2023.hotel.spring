package imb.pr3.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.hotel.entity.Reservacion;


public interface ReservacionRepository extends JpaRepository<Reservacion, Integer> {

}
