package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.NotFoundException;
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
    public VentaFormaPago busquedaPorId(Long id) {
        try {
            return ventaFormaDePagoRepository.findById(id).orElseThrow(() -> new NotFoundException("Vendedor no encontrado con ID: " + id));
        } catch (NotFoundException ex) {
            return null;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
