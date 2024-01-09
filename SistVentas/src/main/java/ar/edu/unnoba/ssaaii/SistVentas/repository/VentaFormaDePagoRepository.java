package ar.edu.unnoba.ssaaii.SistVentas.repository;

import ar.edu.unnoba.ssaaii.SistVentas.model.VentaFormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaFormaDePagoRepository extends JpaRepository<VentaFormaPago,Long> {
}
