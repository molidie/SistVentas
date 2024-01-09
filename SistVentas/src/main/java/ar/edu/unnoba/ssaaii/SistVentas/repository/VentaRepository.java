package ar.edu.unnoba.ssaaii.SistVentas.repository;

import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {
}
