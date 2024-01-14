package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;

import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/venta") //lo dejo, pero seguro lo elimino
public class VentaController {
    @Qualifier("IVentaService")
    private IVentaService ventaService;


    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/new")
    public String nuevaVenta(Model model){
        model.addAttribute("venta",new Venta());
        model.addAttribute("listaArticulos",ventaService.articuloList());
        model.addAttribute("listaVendedores",ventaService.vendedorList());
        model.addAttribute("listaClientes",ventaService.clienteList());
        model.addAttribute("listaFormasPago",ventaService.formaDePagoList());
        return "/Home/Venta/new";
    }

    @PostMapping
    public String crearVenta(Venta venta ){
        ventaService.create(venta);
        return "redirect:/venta/new";
    }




}
