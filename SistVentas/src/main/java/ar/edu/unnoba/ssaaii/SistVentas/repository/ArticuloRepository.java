package ar.edu.unnoba.ssaaii.SistVentas.repository;


import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo,Long> {
    public List<Articulo> findArticuloByOrderByStockDesc();
}
