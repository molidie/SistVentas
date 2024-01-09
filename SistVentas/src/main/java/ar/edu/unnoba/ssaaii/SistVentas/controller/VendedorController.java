package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Vendedor;
import ar.edu.unnoba.ssaaii.SistVentas.service.IVendedorService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String nuevoVendedor(Model model){
        model.addAttribute("vendedor",new Vendedor());
        return "/Home/Vendedor/newVendedor";
    }

    @PostMapping
    public String crearVendedor(Vendedor vendedor){
        vendedorService.create(vendedor);
        return "redirect:/vendedor/listado";
    }

    @GetMapping("/listado")
    public String listarVendedores(Model model) {
        List<Vendedor> vendedores = vendedorService.getAll();
        model.addAttribute("vendedores", vendedores);
        return "/Home/Vendedor/listaVendedores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        vendedorService.delete(id);
        return "redirect:/vendedor/listado";
    }

    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable Long id,Model model){
        model.addAttribute("vendedor",vendedorService.busquedaPorId(id));
        return "/Home/Vendedor/detalles";
    }


}
