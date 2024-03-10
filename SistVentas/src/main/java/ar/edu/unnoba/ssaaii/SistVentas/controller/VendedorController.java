package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.DuplicateEmailException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Vendedor;
import ar.edu.unnoba.ssaaii.SistVentas.model.Proveedor;
import ar.edu.unnoba.ssaaii.SistVentas.model.Vendedor;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVendedorService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

    @Qualifier("IVendedorService")
    private final IVendedorService vendedorService;

    public VendedorController(IVendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping("/new")
    public String nuevoVendedor(Model model, @RequestParam(name = "error", required = false) String errorMessage) {
        model.addAttribute("vendedor", new Vendedor());
        model.addAttribute("errorMessage", errorMessage);
        return "/Home/Vendedor/newVendedor";
    }
    @PostMapping
    public String crearVendedor(Vendedor vendedor, Model model) {
        try {
            vendedorService.create(vendedor);
            return "redirect:/vendedor/listado";
        } catch (DuplicateEmailException ex) {
            return "redirect:/vendedor/new?error=Correo+electronico+ya+existente"; // dejamos este mensaje se puede agregar el de DuplicateEmailException pero hay que trabajarolo con el front
        }
    }


    @GetMapping("/listado")
    public String listarVendedores(Model model) {
        List<Vendedor> vendedores = vendedorService.getAll();
        model.addAttribute("vendedores", vendedores);
        return "/Home/Vendedor/listaVendedores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        vendedorService.delete(id);
        return "redirect:/vendedor/listado";
    }

    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable Long id, Model model) {
        model.addAttribute("vendedor", vendedorService.busquedaPorId(id));
        return "/Home/Vendedor/detalles";
    }

    @GetMapping("/editar/{idE}")
    public String editar(@PathVariable("idE") Long id, Model model) {
        Vendedor vendedor = vendedorService.busquedaPorId(id);
        model.addAttribute("vendedor", vendedor);
        return "/Home/Vendedor/editarVendedor";
    }

    @PostMapping("/editarVendedor/{idE}")
    private String editarArt(@PathVariable("idE") Long id, @ModelAttribute Vendedor ven, Model model) {
        Vendedor vendedor = vendedorService.busquedaPorId(id);
        model.addAttribute("vendedor",ven);
        vendedor.setId(ven.getId());
        vendedor.setNombre(ven.getNombre());
        vendedor.setApellido(ven.getApellido());
        vendedor.setDireccion(ven.getDireccion());
        // Guarda el artículo actualizado
        vendedorService.create(vendedor);
        return "redirect:/vendedor/detalles/{id}";
    }




}