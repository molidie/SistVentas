package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Cliente;
import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;

import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.service.IClienteService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/venta")
public class VentaController {
    @Qualifier("IVentaService")
    private IVentaService ventaService;

    @Qualifier("IClienteService")
    private IClienteService clienteService;


    public VentaController(IVentaService ventaService, IClienteService clienteService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
    }

    @GetMapping("/new")
    public String nuevaVenta(Model model){
        model.addAttribute("venta",new Venta());
        model.addAttribute("listaArticulos",ventaService.articuloList());
        model.addAttribute("listaVendedores",ventaService.vendedorList());
        model.addAttribute("listaClientes",ventaService.clienteList());
        model.addAttribute("listaFormasPago",ventaService.formaDePagoList());
        model.addAttribute("listaVenta",ventaService.getAll());
        return "/Home/Venta/new";
    }

    @PostMapping
    public String crearVenta(Venta venta ){
        ventaService.create(venta);
        Long clienteid = venta.getCliente().getId();
        Cliente cliente =  clienteService.busquedaPorId(clienteid);
        cliente.getVentas().add(venta);
        System.out.println(cliente.getVentas().size());
        return "redirect:/venta/new";
    }

    @GetMapping("/cancelar/{idV}")
    public String cancelarVenta(@PathVariable Long idV) {
        Venta venta = ventaService.busquedaPorId(idV);

        // Itera sobre los VentaArticulo de la venta para devolver a stock la cantidad
        for (VentaArticulo ventaArticulo : venta.getVentaArticulos()) {
            Articulo articulo = ventaArticulo.getArticulo();
            articulo.setStock(articulo.getStock() + ventaArticulo.getCantidad());
        }

        // Elimina la venta (esto también eliminará los VentaArticulo debido a la cascada)
        ventaService.delete(idV);

        return "redirect:/venta/new";
    }




}
