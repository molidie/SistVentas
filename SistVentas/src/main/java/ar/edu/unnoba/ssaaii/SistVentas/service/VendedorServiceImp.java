package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.Vendedor;
import ar.edu.unnoba.ssaaii.SistVentas.repository.VendedorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorServiceImp implements IVendedorService, UserDetailsService {
    private final VendedorRepository vendedorRepository;

    public VendedorServiceImp(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public Vendedor create(Vendedor vendedor) {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        for(Vendedor v : vendedores){
            if(v.getId().equals(vendedor.getId())){ //lo podriamos cambiar
                return null; //hagamosle un try catch
            }
        }
        vendedorRepository.save(vendedor);
        return vendedor;
    }

    @Override
    public List<Vendedor> getAll() {
        return vendedorRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        vendedorRepository.deleteById(id);
    }
    @Override
    public Vendedor busquedaPorId(Long id) {
        try {
            return vendedorRepository.findById(id).orElseThrow(() -> new NotFoundException("Vendedor no encontrado con ID: " + id));
        } catch (NotFoundException ex) {
            return null;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
