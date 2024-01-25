package ar.edu.unnoba.ssaaii.SistVentas.repository;

import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;
import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaArticuloRepository extends JpaRepository<VentaArticulo,Long> {

}
