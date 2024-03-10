package ar.edu.unnoba.ssaaii.SistVentas.service;
import ar.edu.unnoba.ssaaii.SistVentas.exeption.NotFoundException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Proveedor;
import ar.edu.unnoba.ssaaii.SistVentas.repository.ProveedorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImp implements IProveedorService, UserDetailsService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImp(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public Proveedor create (Proveedor proveedor) { 
        proveedorRepository.save(proveedor);
        return proveedor;
    }

    @Override
    public List<Proveedor> getAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        proveedorRepository.deleteById(id);
    }
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    @Override
    public Proveedor busquedaPorId(Long id) {
        try {
            return proveedorRepository.findById(id).orElseThrow(() -> new NotFoundException("Proveedor no encontrado con ID: " + id));
        } catch (NotFoundException ex) {
            return null;
        }
    }
    
}
