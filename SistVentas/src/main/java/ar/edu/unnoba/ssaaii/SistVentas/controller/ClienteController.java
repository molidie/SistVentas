package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Cliente;
import ar.edu.unnoba.ssaaii.SistVentas.service.IClienteService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Qualifier("IClienteService")
    private IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/new")
    public String nuevoCliente(Model model){
        model.addAttribute("cliente",new Cliente());
        model.addAttribute("listaclientes",clienteService.getAll());
        return "/Home/Cliente/new";
    }

    @PostMapping
    public String crearCliente(Cliente cliente){
        clienteService.create(cliente);
        return "redirect:/clientes/new";
    }


    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable Long id,Model model){
        model.addAttribute("cliente",clienteService.busquedaPorId(id));
        return "/Home/Cliente/detalles";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        clienteService.delete(id);
        return "redirect:/clientes/new";
    }

    @GetMapping("/editar/{idE}")
    public String editar(@PathVariable("idE") Long id, Model model) {
        Cliente cliente = clienteService.busquedaPorId(id);
        model.addAttribute("cliente", cliente);
        return "/Home/Cliente/editar";
    }

    @PostMapping("/editarCliente/{idE}")
    private String editarArt(@PathVariable("idE") Long id, @ModelAttribute Cliente art, Model model) {
        Cliente articulo = clienteService.busquedaPorId(id);
        model.addAttribute("articulo",art);
        articulo.setId(art.getId());
        articulo.setNombre(art.getNombre());
        articulo.setApellido(art.getApellido());
        articulo.setDireccion(art.getDireccion());
        articulo.setVentas(art.getVentas());
        // Guarda el artículo actualizado
        clienteService.create(articulo);
        return "redirect:/clientes/new";
    }



}
