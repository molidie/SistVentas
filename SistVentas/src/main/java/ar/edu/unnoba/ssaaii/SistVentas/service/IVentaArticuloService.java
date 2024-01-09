package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVentaArticuloService {
    public VentaArticulo create(VentaArticulo ventaArticulo);
    public List<VentaArticulo> getAll();
    public void delete(Long id);
}
