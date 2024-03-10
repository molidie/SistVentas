package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Proveedor;
import ar.edu.unnoba.ssaaii.SistVentas.repository.ProveedorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProveedorService {
    public Proveedor create(Proveedor proveedor);
    public List<Proveedor> getAll();
    public void delete(Long id);
    Proveedor busquedaPorId(Long id);
}
