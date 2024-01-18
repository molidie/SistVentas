package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.StockInsuficienteException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;
import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.service.IArticuloService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaArticuloService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaService;
import ar.edu.unnoba.ssaaii.SistVentas.service.StockService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/ventaArticulo") //con este creamos la venta
public class VentaArticuloController {
    @Qualifier("IVentaArticuloService")
    private IVentaArticuloService ventaArticuloService;
    @Qualifier("IVentaService")
    private IVentaService ventaService;
    @Qualifier("IArticuloService")
    private IArticuloService articuloService;
    @Qualifier("StockService")
    private StockService stockService;

    public VentaArticuloController(IVentaArticuloService ventaArticuloService, IVentaService ventaService, IArticuloService articuloService, StockService stockService) {
        this.ventaArticuloService = ventaArticuloService;
        this.ventaService = ventaService;
        this.articuloService = articuloService;
        this.stockService = stockService;
    }

    @GetMapping("/new")
    public String nuevaVenta(Model model){
        model.addAttribute("ventaArticulo", new VentaArticulo());
        cargarDatosComunes(model);
        return "/Home/VentaAticulo/new";
    }

    @PostMapping("/new")
    public String crearVenta(@ModelAttribute("ventaArticulo") VentaArticulo ventaArticulo, Model model) {
        try {
            Venta venta = ventaArticulo.getVenta();
            ventaService.create(venta);
            venta.setFecha(ventaArticulo.getVenta().getFecha());
            ventaArticulo.setVenta(venta);
            Articulo articulo = articuloService.busquedaPorId(ventaArticulo.getArticulo().getId());

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

            ventaArticuloService.create(ventaArticulo);
        } catch (StockInsuficienteException e) {
            model.addAttribute("stockInsuficiente", true);
            cargarDatosComunes(model);
            return "/Home/VentaAticulo/new";
        }


        return "redirect:/ventaArticulo/new";
    }

    private void cargarDatosComunes(Model model) {
        model.addAttribute("listaClientes", ventaService.clienteList());
        model.addAttribute("listaVendedor", ventaService.vendedorList());
        model.addAttribute("listaArticulos", ventaService.articuloList());
        model.addAttribute("listaFormaPago", ventaService.formaDePagoList());
        model.addAttribute("listaVentaArticulo", ventaArticuloService.getAll());
        List<VentaArticulo> listaVentaArticulo = ventaArticuloService.getAll();
        double montoTotal = listaVentaArticulo.stream().mapToDouble(VentaArticulo::getMonto).sum();
        model.addAttribute("montototal", montoTotal);
        model.addAttribute("tabla",ventaArticuloService.obtenerMontoYPorcentajePorMes());

    }


    @GetMapping("/cargarDatosArticulo/{articuloId}")
    @ResponseBody
    public Articulo cargarDatosArticulo(@PathVariable Long articuloId) {
        return articuloService.busquedaPorId(articuloId);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        VentaArticulo ventaArticulo = ventaArticuloService.busquedaPorId(id);
        Articulo articulo = ventaArticulo.getArticulo();
        articulo.setStock(articulo.getStock() + ventaArticulo.getCantidad());
        ventaArticuloService.delete(id);
        return "redirect:/ventaArticulo/new";
    }
    @GetMapping("/infoVentas")
    public String infoVentas(Model model){
        model.addAttribute("tabla",ventaArticuloService.obtenerMontoYPorcentajePorMes());
        String[] meses = new DateFormatSymbols(new Locale("es")).getMonths();
        model.addAttribute("meses", meses);
        return "/Home/VentaAticulo/informeDeVentas";

    }

}

