package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Proveedor;
import ar.edu.unnoba.ssaaii.SistVentas.service.IArticuloService;
import ar.edu.unnoba.ssaaii.SistVentas.service.IProveedorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {
    @Qualifier("IArticuloService")
    private IArticuloService articuloService;
    @Qualifier("IProveedorService")
    private IProveedorService proveedorService;

    public ArticuloController(IArticuloService articuloService, IProveedorService proveedorService) {
        this.articuloService = articuloService;
        this.proveedorService= proveedorService;
    }

@GetMapping("/new")
public String nuevoArticulo(Model model) {
    Articulo articulo = new Articulo();
    List<Proveedor> proveedores = proveedorService.getAll();
    List<Articulo> articulos = articuloService.getAll();

    boolean hayErrorLimiteStock = articulos.stream().anyMatch(a -> a.getStock() <= a.getStock_min());

    model.addAttribute("art", articulo);
    model.addAttribute("listaArticulos", articulos);
    model.addAttribute("listaProveedores", proveedores);
    model.addAttribute("errorlimitestock", hayErrorLimiteStock);
    model.addAttribute("navbarIndicator", hayErrorLimiteStock);

    return "/Home/Articulo/newArt";
}


@PostMapping
public String create(@ModelAttribute Articulo articulo, @RequestParam("proveedor") Long proveedorId) {
    // Obtén el proveedor por su ID
    Proveedor proveedor = proveedorService.busquedaPorId(proveedorId);
    
    // Asigna el proveedor al artículo
    articulo.setProveedor(proveedor);

    // Guarda el artículo actualizado
    articuloService.create(articulo);

    return "redirect:/articulos/new";
}



    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        articuloService.delete(id);
        return "redirect:/articulos/new";

    }

    @GetMapping("/detalle/{id}")
    public String detalleArticulo(@PathVariable Long id, Model model) {
        Articulo articulo = articuloService.busquedaPorId(id);
        model.addAttribute("articulo", articulo);
        return "/Home/Articulo/detallesArticulo";
    }
    
    @GetMapping("/stockmin/{idE}")
    public String modificarStockMinimo(@PathVariable("idE") Long id, Model model) {
        Articulo articulo = articuloService.busquedaPorId(id);
        model.addAttribute("articulo", articulo);
        return "/Home/Articulo/stockmin";
    }
    @PostMapping("/editarMin/{idE}")
    private String editarMin(@PathVariable("idE") Long id, @ModelAttribute Articulo art,Model model) {
        Articulo articulo = articuloService.busquedaPorId(id);
        model.addAttribute("articulo",art);
        articulo.setId(art.getId());
        articulo.setStock_min(art.getStock_min());
        // Guarda el artículo actualizado
        articuloService.create(articulo);
        return "redirect:/articulos/new";
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
        articulo.setFechaIngreso(art.getFechaIngreso());
        articulo.setVentaArticulos(art.getVentaArticulos());
        // Guarda el artículo actualizado
        articuloService.create(articulo);
        return "redirect:/articulos/new";
    }





}
