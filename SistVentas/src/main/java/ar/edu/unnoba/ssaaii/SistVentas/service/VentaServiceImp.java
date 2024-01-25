package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.*;
import ar.edu.unnoba.ssaaii.SistVentas.repository.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImp implements IVentaService, UserDetailsService {
    private final VentaRepository ventaRepository;
    private final ArticuloRepository articuloRepository;
    private final VendedorRepository vendedorRepository;
    private final ClienteRepository clienteRepository;
    private final FormaDePagoRepository formaDePagoRepository;

    public VentaServiceImp(VentaRepository ventaRepository, ArticuloRepository articuloRepository, VendedorRepository vendedorRepository, ClienteRepository clienteRepository, FormaDePagoRepository formaDePagoRepository) {
        this.ventaRepository = ventaRepository;
        this.articuloRepository = articuloRepository;
        this.vendedorRepository = vendedorRepository;
        this.clienteRepository = clienteRepository;
        this.formaDePagoRepository = formaDePagoRepository;

    }

    @Override
    public Venta create(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public List<Venta> getAll() {
        return ventaRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public List<Articulo> articuloList(){
        return articuloRepository.findAll();
    }
    @Override
    public List<Vendedor> vendedorList(){
        return vendedorRepository.findAll();
    }

    @Override
    public List<Cliente> clienteList(){
        return clienteRepository.findAll();
    }
    @Override
    public List<FormaDePago> formaDePagoList(){
        return formaDePagoRepository.findAll();
    }

    @Override
    public List<Venta> ventasPorMes() {
        return ventaRepository.findAllByOrderByFechaAsc();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

