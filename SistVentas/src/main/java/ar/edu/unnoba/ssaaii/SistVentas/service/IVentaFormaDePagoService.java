package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.VentaFormaPago;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVentaFormaDePagoService {
    public VentaFormaPago create(VentaFormaPago ventaFormaPago);
    public List<VentaFormaPago> getAll();
    public void delete(Long id);
}
