package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.service.IArticuloService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {
    @Qualifier("IArticuloService")
    private IArticuloService articuloService;

    public ArticuloController(IArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping("/new")
    public String nuevoArticulo(Model model){
        model.addAttribute("art",new Articulo());
        model.addAttribute("listaArticulos",articuloService.getAll());
        return "/Home/Articulo/newArt";
    }

    @PostMapping
    public String create(Articulo articulo){
        articuloService.create(articulo);
        return "redirect:/articulos/new";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        articuloService.delete(id);
        return "redirect:/articulos/new";

    }

    @GetMapping
    public String detalles(@PathVariable Long id,Model model){
        model.addAttribute("articulo",articuloService.busquedaPorId(id));
        return "/Home/Articulo/newArt";
    }

    @GetMapping("/editar/{idE}")
    public String editar(@PathVariable("idE") Long id, Model model) {
        Articulo articulo = articuloService.busquedaPorId(id);
        model.addAttribute("articulo", articulo);
        return "/Home/Articulo/editar";
    }

    @PostMapping("/editarArt/{idE}")
    private String editarArt(@PathVariable("idE") Long id, @ModelAttribute Articulo art,Model model) {
        Articulo articulo = articuloService.busquedaPorId(id);
        model.addAttribute("articulo",art);
        articulo.setId(art.getId());
        articulo.setNombre(art.getNombre());
        articulo.setPrecio(art.getPrecio());
        articulo.setStock(art.getStock());
        articulo.setVentaArticulos(art.getVentaArticulos());
        // Guarda el artículo actualizado
        articuloService.create(articulo);
        return "redirect:/articulos/new";
    }



}
