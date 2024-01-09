package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.Vendedor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("IVendedorService")
public interface IVendedorService {
    public Vendedor create(Vendedor vendedor);
    public List<Vendedor> getAll();
    public void delete(Long id);
    public Vendedor busquedaPorId(Long id);
}
