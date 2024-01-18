package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IVentaArticuloService {
    public VentaArticulo create(VentaArticulo ventaArticulo);
    public List<VentaArticulo> getAll();
    public void delete(Long id);

    VentaArticulo busquedaPorId(Long id);

    List<Map<String, Object>> obtenerMontoYPorcentajePorMes();
}
