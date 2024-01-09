package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.FormaDePago;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFormaDePagoService {
    public FormaDePago create(FormaDePago formaDePago);
    public List<FormaDePago> getAll();
    public void delete(Long id);
}
