package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IArticuloService {
    public Articulo create(Articulo articulo);
    public List<Articulo> getAll();
    public void delete(Long id);

    Articulo busquedaPorId(Long id);

    public List<Articulo> ordenarPorMasVendido();
}
