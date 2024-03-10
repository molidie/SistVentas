package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.FormaDePago;
import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;
import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.VentaFormaPago;
import ar.edu.unnoba.ssaaii.SistVentas.service.IFormaDePagoService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaFormaDePagoService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/ventaFormaDePago")
public class VentaFormaDePagoController {

    @Autowired
    private IVentaFormaDePagoService ventaFormaDePagoService;
    @Autowired
    private IFormaDePagoService formaDePagoService;

    @Autowired
    private IVentaService ventaService;

    private final List<VentaFormaPago> ventaFormaPagos = new ArrayList<>();

    public VentaFormaDePagoController(IVentaFormaDePagoService ventaFormaDePagoService,
            IFormaDePagoService formaDePagoService, IVentaService ventaService) {
        this.ventaFormaDePagoService = ventaFormaDePagoService;
        this.formaDePagoService = formaDePagoService;
        this.ventaService = ventaService;
    }

    @GetMapping("/new/{id}")
    public String formaDePago(Model model, @PathVariable Long id) {
        VentaFormaPago ventaFormaPago = new VentaFormaPago();
        Venta venta = ventaService.busquedaPorId(id);
        model.addAttribute("ventaFormaDePago", ventaFormaPago);
        model.addAttribute("venta", venta);
        model.addAttribute("cuotas", ventaFormaPago.getCuotas());
        model.addAttribute("formasDePago", formaDePagoService.getAll());
        return "/Home/VentaFormaDePago/new";
    }

    @PostMapping("/new/{idP}")
    public String nuevaVentaFormaDePago(@PathVariable Long idP, Model model, VentaFormaPago ventaFormaPago) {
        Venta venta = ventaService.busquedaPorId(idP);
        float montoVenta = 0f;

        for (VentaArticulo vA : venta.getVentaArticulos()) {
            if (Objects.equals(vA.getVenta().getId(), venta.getId())) {
                montoVenta += vA.getMonto();
            }
        }

        model.addAttribute("venta", venta);
        model.addAttribute("ventaFormaDePago", ventaFormaPago);
        model.addAttribute("formasDePago", formaDePagoService.getAll());

        float valorCuotas = montoVenta / ventaFormaPago.getCuotas();
        model.addAttribute("valorCuotas", valorCuotas);

        float valorInteresPorCuota = ventaFormaPago.getInteres() / 100;
        float montoConInteres = montoVenta * (1 + valorInteresPorCuota);
        float valorCuotaConInteres = montoConInteres / ventaFormaPago.getCuotas();
        model.addAttribute("valorInteresPorCuota", valorInteresPorCuota);

        model.addAttribute("valorVenta", montoVenta);

        float valorFinal = montoConInteres * 1.21f;

        if (Objects.equals(ventaFormaPago.getFormaDePago().getTipo(), "EFECTIVO")) {
            valorFinal = (montoVenta) - ((montoVenta * (ventaFormaPago.getDescuento() / 100)));
            valorFinal = valorFinal * 1.21f;
            model.addAttribute("descuento", ventaFormaPago.getDescuento());
        }

        model.addAttribute("valorfinal", valorFinal);
        ventaFormaPago.setMonto(valorFinal);
        ventaFormaPago.setVenta(venta);
        ventaFormaPagos.add(ventaFormaPago);
        return "redirect:/ventaFormaDePago/new/" + idP; // Redirecciona al formulario de forma de pago
    }

    @GetMapping("/guardar/{idP}")
    public String guardarVentaFormaDePago(@PathVariable Long idP) {
        int index = ventaFormaPagos.size();
        ventaFormaDePagoService.create(ventaFormaPagos.get(index - 1));
        Long id = ventaFormaPagos.get(index - 1).getId();
        ventaFormaPagos.clear();
        return "redirect:/ventaFormaDePago/nota/" + idP + "/" + id;
    }

    @GetMapping("/nota/{idV}/{idN}")
    public String notaDeCompra(@PathVariable Long idV, @PathVariable Long idN, Model model) {
        Venta venta = ventaService.busquedaPorId(idV);
        VentaFormaPago ventaFormaPago = ventaFormaDePagoService.busquedaPorId(idN);
        float montoVenta = 0f;
    
        // Calcular el valor por unidad de los objetos de la venta
        List<Float> valoresPorUnidad = new ArrayList<>();
        for (VentaArticulo vA : venta.getVentaArticulos()) {
            if (Objects.equals(vA.getVenta().getId(), venta.getId())) {
                montoVenta += vA.getMonto();
                float valorPorUnidad = vA.getMonto() / vA.getCantidad();
                valoresPorUnidad.add(valorPorUnidad);
            }
        }
        model.addAttribute("ventasDeArticulos", venta.getVentaArticulos());
        model.addAttribute("cliente", venta.getCliente().getNombre() + " " + venta.getCliente().getApellido());
        model.addAttribute("Vendedor", venta.getVendedor().getNombre()+ " " + venta.getVendedor().getApellido());
        model.addAttribute("codigoDeVenta", venta.getId());
        model.addAttribute("montoTotalVenta", montoVenta);
        model.addAttribute("cuotas", ventaFormaPago.getCuotas());
        model.addAttribute("interes", ventaFormaPago.getInteres());
        model.addAttribute("Descuento", ventaFormaPago.getDescuento());
        model.addAttribute("Total", ventaFormaPago.getMonto());
        model.addAttribute("tipoFormaDePago", ventaFormaPago.getFormaDePago().getTipo());
        model.addAttribute("fechaEmision", venta.getFecha());
        model.addAttribute("valoresPorUnidad", valoresPorUnidad);

        return "/Home/VentaFormaDePago/nota";
    }

}
