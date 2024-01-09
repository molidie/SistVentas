package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.FormaDePago;
import ar.edu.unnoba.ssaaii.SistVentas.repository.FormaDePagoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaDePagoServiceImp implements IFormaDePagoService, UserDetailsService {
    private final FormaDePagoRepository formaDePagoRepository;

    public FormaDePagoServiceImp(FormaDePagoRepository formaDePagoRepository) {
        this.formaDePagoRepository = formaDePagoRepository;
    }

    @Override
    public FormaDePago create(FormaDePago formaDePago) {
        formaDePagoRepository.save(formaDePago);
        return formaDePago;
    }

    @Override
    public List<FormaDePago> getAll() {
        return formaDePagoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        formaDePagoRepository.deleteById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
