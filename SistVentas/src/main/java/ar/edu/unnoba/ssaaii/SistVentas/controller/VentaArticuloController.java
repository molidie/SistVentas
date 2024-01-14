package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;
import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.service.IArticuloService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaArticuloService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ventaArticulo") //con este creamos la venta
public class VentaArticuloController {
    @Qualifier("IVentaArticuloService")
    private IVentaArticuloService ventaArticuloService;
    @Qualifier("IVentaService")
    private IVentaService ventaService;
    @Qualifier("IArticuloService")

    private IArticuloService articuloService;

    public VentaArticuloController(IVentaArticuloService ventaArticuloService, IVentaService ventaService, IArticuloService articuloService) {
        this.ventaArticuloService = ventaArticuloService;
        this.ventaService = ventaService;
        this.articuloService = articuloService;
    }

    @GetMapping("/new")
    public String nuevaVenta(Model model){
        model.addAttribute("ventaArticulo", new VentaArticulo());
        model.addAttribute("listaClientes",ventaService.clienteList());
        model.addAttribute("listaVendedor",ventaService.vendedorList());
        model.addAttribute("listaArticulos",ventaService.articuloList());
        model.addAttribute("listaFormaPago",ventaService.formaDePagoList());
        model.addAttribute("listaVentaArticulo",ventaArticuloService.getAll());
        return "/Home/VentaAticulo/new";
    }

    @PostMapping("/new")
    public String crearVenta(VentaArticulo ventaArticulo) {
        Venta venta = ventaArticulo.getVenta();
        ventaService.create(venta);
        venta.setFecha(ventaArticulo.getVenta().getFecha());
        ventaArticulo.setVenta(venta);
        Articulo articulo = articuloService.busquedaPorId(ventaArticulo.getArticulo().getId());
        if (ventaArticulo.getArticulo() != null) {
            float monto = articulo.getPrecio() * ventaArticulo.getCantidad();
            ventaArticulo.setMonto(monto);
        }
        ventaArticuloService.create(ventaArticulo);

        return "redirect:/ventaArticulo/new";
    }

    @GetMapping("/cargarDatosArticulo/{articuloId}")
    @ResponseBody
    public Articulo cargarDatosArticulo(@PathVariable Long articuloId) {
        return articuloService.busquedaPorId(articuloId);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        ventaArticuloService.delete(id);
        return "redirect:/ventaArticulo/new";
    }
}

