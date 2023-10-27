package imb.pr3.hotel.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.hotel.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}