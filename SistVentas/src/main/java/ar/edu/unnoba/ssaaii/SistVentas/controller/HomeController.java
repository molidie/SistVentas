package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Vendedor;
import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.service.IArticuloService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IClienteService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVendedorService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaArticuloService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Qualifier("IArticuloService")
    private IArticuloService articuloService;
    @Qualifier("IVentaArticuloService")
    private IVentaArticuloService ventaArticuloService;

    @Qualifier("IVendedorService")
    private IVendedorService vendedorService;

    @Qualifier("IClienteService")
    private IClienteService clienteService;

    public HomeController(IArticuloService articuloService, IVentaArticuloService ventaArticuloService, IVendedorService vendedorService, IClienteService clienteService) {
        this.articuloService = articuloService;
        this.ventaArticuloService = ventaArticuloService;
        this.vendedorService = vendedorService;
        this.clienteService = clienteService;
    }

    @GetMapping("/index")
    public ResponseEntity<List<Articulo>> obtenerProductosMasVendidos() {
        List<Articulo> articulosMasVendidos = articuloService.ordenarPorMasVendido();
        return new ResponseEntity<>(articulosMasVendidos, HttpStatus.OK);
    }

    @GetMapping("/ventasPorMes")
    public ResponseEntity<List<Map<String, Object>>> obtenerVentasPorMes() {
        List<Map<String, Object>> ventasPorMes = ventaArticuloService.obtenerMontoPorMes();
        return new ResponseEntity<>(ventasPorMes, HttpStatus.OK);
    }

    @GetMapping("/porcentajeCambioPorMes")
    public ResponseEntity<List<Map<String, Object>>> obtenerPorcentajeCambioPorMes() {
        List<Map<String, Object>> porcentajeCambioPorMes = ventaArticuloService.obtenerPorcentajeCambioPorMes();
        return new ResponseEntity<>(porcentajeCambioPorMes, HttpStatus.OK);
    }

    @GetMapping
    public String mostrarDashboard(Model model) {
        model.addAttribute("totalVendedores", vendedorService.getAll());
        model.addAttribute("totalClientes", clienteService.getAll());
        model.addAttribute("totalArticulos", articuloService.getAll());
        return "/Home/home";
    }


}
