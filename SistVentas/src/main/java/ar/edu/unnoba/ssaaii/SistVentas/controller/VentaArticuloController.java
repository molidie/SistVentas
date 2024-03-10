package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.StockInsuficienteException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Cliente;
import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;
import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.service.IArticuloService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaArticuloService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaService;
import ar.edu.unnoba.ssaaii.SistVentas.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.text.DecimalFormat;

@Controller
@RequestMapping("/ventaArticulo") // con este creamos la venta
public class VentaArticuloController {
    @Autowired
    private IVentaArticuloService ventaArticuloService;
    @Autowired
    private IVentaService ventaService;
    @Autowired
    private IArticuloService articuloService;

    public VentaArticuloController(IVentaArticuloService ventaArticuloService, IVentaService ventaService,
            IArticuloService articuloService) {
        this.ventaArticuloService = ventaArticuloService;
        this.ventaService = ventaService;
        this.articuloService = articuloService;
    }

    @GetMapping("/new/{id}")
    public String nuevo(@PathVariable Long id, Model model) {
        VentaArticulo ventaArticulo = new VentaArticulo();
        float montoT = 0f;
        Venta venta = ventaService.busquedaPorId(id);
        model.addAttribute("ventaArticulo", ventaArticulo);
        model.addAttribute("venta", venta);
        model.addAttribute("articulos", articuloService.getAll());
        model.addAttribute("articulosVendidos", venta.getVentaArticulos());
        for (VentaArticulo vA : venta.getVentaArticulos()) {
            montoT += vA.getMonto();
        }
        model.addAttribute("montoTotalVentaArticulo", montoT);

        return "/Home/VentaAticulo/new";
    }

    @PostMapping("/new/{idE}")
    public String crear(@PathVariable Long idE, Model model, VentaArticulo ventaArticulo) {
        model.addAttribute("ventaDeArticulo", ventaArticulo);
        Venta venta = ventaService.busquedaPorId(idE);
        model.addAttribute("venta", venta);
        ventaArticulo.setVenta(venta);
        float montoT = 0f;
        try {
            Articulo articulo = articuloService.busquedaPorId(ventaArticulo.getArticulo().getId());
            model.addAttribute("articulos", articulo);
            if (ventaArticulo.getArticulo() != null) {
                float monto = articulo.getPrecio() * ventaArticulo.getCantidad();
                ventaArticulo.setMonto(monto);
            }

            if (ventaArticulo.getCantidad() >= articulo.getStock()) {
                throw new StockInsuficienteException("La cantidad supera al stock disponible");
            } else {
                int numero = articulo.getStock() - ventaArticulo.getCantidad();
                articulo.setStock(numero);
            }

        } catch (StockInsuficienteException e) {
            model.addAttribute("stockInsuficiente", true);
            return "/Home/VentaAticulo/new";
        }
        ventaArticuloService.create(ventaArticulo);
        venta.getVentaArticulos().add(ventaArticulo);
        for (VentaArticulo vA : venta.getVentaArticulos()) {
            montoT += vA.getMonto();
        }
        model.addAttribute("montoTotalVentaArticulo", montoT);
        return "redirect:/ventaArticulo/new/" + idE;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        VentaArticulo ventaArticulo = ventaArticuloService.busquedaPorId(id);
        Long idV = ventaArticulo.getVenta().getId();
        Articulo articulo = ventaArticulo.getArticulo();
        articulo.setStock(articulo.getStock() + ventaArticulo.getCantidad());
        ventaArticuloService.delete(id);
        return "redirect:/ventaArticulo/new/" + idV;
    }

    @GetMapping("/cancelar/{idV}")
    public String cancelar(@PathVariable Long idV, Model model) {
        return "redirect:/venta/new";
    }

    @GetMapping("/infoVentas")
    public String infoVentas(Model model) {
        model.addAttribute("tabla", ventaArticuloService.obtenerMontoYPorcentajePorMes());
        String[] meses = new DateFormatSymbols(new Locale("es")).getMonths();
        model.addAttribute("meses", meses);
        return "/Home/VentaAticulo/informeDeVentas";

    }

}
