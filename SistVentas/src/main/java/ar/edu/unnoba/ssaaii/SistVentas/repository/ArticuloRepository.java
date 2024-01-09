package ar.edu.unnoba.ssaaii.SistVentas.repository;


import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,Long> {
}
