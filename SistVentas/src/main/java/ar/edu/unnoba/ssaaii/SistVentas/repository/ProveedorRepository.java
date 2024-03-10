package ar.edu.unnoba.ssaaii.SistVentas.repository;
import ar.edu.unnoba.ssaaii.SistVentas.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
}

