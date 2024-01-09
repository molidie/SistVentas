package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVentaService {
    public Venta create(Venta venta);
    public List<Venta> getAll();
    public void delete(Long id);
}
