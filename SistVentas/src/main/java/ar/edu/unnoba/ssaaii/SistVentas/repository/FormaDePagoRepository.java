package ar.edu.unnoba.ssaaii.SistVentas.repository;

import ar.edu.unnoba.ssaaii.SistVentas.model.FormaDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaDePagoRepository extends JpaRepository<FormaDePago,Long> {
}
