package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.NotFoundException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.repository.ArticuloRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticuloServiceImp implements IArticuloService, UserDetailsService {
    private final ArticuloRepository articuloRepository;

    public ArticuloServiceImp(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }


    @Override
    public Articulo create(Articulo articulo) { //por ahi lo podemos hacer por codigo de barra en vez de nombre

        articuloRepository.save(articulo);
        return articulo;
    }

    @Override
    public List<Articulo> getAll() {
        return articuloRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        articuloRepository.deleteById(id);

    }

    @Override
    public Articulo busquedaPorId(Long id) {
        try {
            return articuloRepository.findById(id).orElseThrow(() -> new NotFoundException("Vendedor no encontrado con ID: " + id));
        } catch (NotFoundException ex) {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
