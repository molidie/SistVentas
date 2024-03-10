package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVentaService {
    public Venta create(Venta venta);

    public List<Venta> getAll();
    public void delete(Long id);

    List<Articulo> articuloList();

    List<Vendedor> vendedorList();

    List<Cliente> clienteList();

    List<FormaDePago> formaDePagoList();
    List<Venta> ventasPorMes();
    

    Venta busquedaPorId(Long id);
}
