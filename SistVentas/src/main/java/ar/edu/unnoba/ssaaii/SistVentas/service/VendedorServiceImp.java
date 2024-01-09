package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.DuplicateEmailException;
import ar.edu.unnoba.ssaaii.SistVentas.exeption.NotFoundException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Vendedor;
import ar.edu.unnoba.ssaaii.SistVentas.repository.VendedorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceImp implements IVendedorService, UserDetailsService {
    private final VendedorRepository vendedorRepository;

    public VendedorServiceImp(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public Vendedor create(Vendedor vendedor) throws DuplicateEmailException {
        checkIfEmailExists(vendedor.getMail());
        vendedorRepository.save(vendedor);
        return vendedor;
    }


    private void checkIfEmailExists(String email) {
        List<Vendedor> vendedores = vendedorRepository.findByMail(email);
        if (!vendedores.isEmpty()) {
            throw new DuplicateEmailException("Correo electronico ya existente en la base de datos: ");
        }
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
