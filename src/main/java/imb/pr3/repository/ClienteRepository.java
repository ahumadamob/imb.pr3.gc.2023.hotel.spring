package imb.pr3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}