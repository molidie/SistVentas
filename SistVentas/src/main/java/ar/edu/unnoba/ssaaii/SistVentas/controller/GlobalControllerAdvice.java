package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.service.IArticuloService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice //es un controllador global
public class GlobalControllerAdvice {
    @Qualifier("IArticuloService")
    private IArticuloService articuloService;

    public GlobalControllerAdvice(IArticuloService articuloService) {
        this.articuloService = articuloService;
    }


    @ModelAttribute
    public void globalAttributes(Model model) {
        boolean navbarIndicator = hayErrorLimiteStock();  // Lógica para determinar el valor de navbarIndicator
        model.addAttribute("navbarIndicator", navbarIndicator);
    }

    private boolean hayErrorLimiteStock() {
        List<Articulo> articulos = obtenerTodosLosArticulos();  // Reemplaza con tu lógica para obtener los artículos
        return articulos.stream().anyMatch(a -> a.getStock() <= a.getStock_min());
    }

    // Implementa tu lógica específica para obtener todos los artículos
    private List<Articulo> obtenerTodosLosArticulos() {
        return articuloService.getAll();
    }
}

