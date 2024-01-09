package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.repository.VentaArticuloRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaArticuloServiceImp implements IVentaArticuloService, UserDetailsService {

    private final VentaArticuloRepository ventaArticuloRepository;

    public VentaArticuloServiceImp(VentaArticuloRepository ventaArticuloRepository) {
        this.ventaArticuloRepository = ventaArticuloRepository;
    }

    @Override
    public VentaArticulo create(VentaArticulo ventaArticulo) {
        return ventaArticuloRepository.save(ventaArticulo);
    }

    @Override
    public List<VentaArticulo> getAll() {
        return ventaArticuloRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        ventaArticuloRepository.deleteById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
