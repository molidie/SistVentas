package ar.edu.unnoba.ssaaii.SistVentas.service;

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
        List<Articulo> articulos = getAll();
        for (Articulo a : articulos){
            if (articulo.getNombre().equals(a.getNombre())){
                return a;
            }
        }
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
