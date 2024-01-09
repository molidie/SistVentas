package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.VentaFormaPago;
import ar.edu.unnoba.ssaaii.SistVentas.repository.VentaFormaDePagoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaFormaDePagoServiceImp implements IVentaFormaDePagoService, UserDetailsService {
    private final VentaFormaDePagoRepository ventaFormaDePagoRepository;

    public VentaFormaDePagoServiceImp(VentaFormaDePagoRepository ventaFormaDePagoRepository) {
        this.ventaFormaDePagoRepository = ventaFormaDePagoRepository;
    }

    @Override
    public VentaFormaPago create(VentaFormaPago ventaFormaPago) {
        return ventaFormaDePagoRepository.save(ventaFormaPago);
    }

    @Override
    public List<VentaFormaPago> getAll() {
        return ventaFormaDePagoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        ventaFormaDePagoRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
