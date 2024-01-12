package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClienteService {
    public Cliente create(Cliente cliente);
    public List<Cliente> getAll();
    public void delete(Long id);

    Cliente busquedaPorId(Long id);
}
