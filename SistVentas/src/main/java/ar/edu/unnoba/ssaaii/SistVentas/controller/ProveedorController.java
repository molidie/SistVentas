package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.DuplicateEmailException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Proveedor;
import ar.edu.unnoba.ssaaii.SistVentas.service.IProveedorService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    @Qualifier("IProveedorService")
    private final IProveedorService proveedorService;

    public ProveedorController(IProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/new")
    public String nuevoProveedor(Model model, @RequestParam(name = "error", required = false) String errorMessage) {
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("errorMessage", errorMessage);
        return "/Home/Proveedor/newProveedor";
    }

    @PostMapping
    public String crearProveedor(Proveedor proveedor, Model model) {
        try {
            proveedorService.create(proveedor);
            return "redirect:/proveedor/listado";
        } catch (DuplicateEmailException ex) {
            return "redirect:/proveedor/new?error=Correo+electronico+ya+existente"; // dejamos este mensaje se puede agregar el de DuplicateEmailException pero hay que trabajarolo con el front
        }
    }


    @GetMapping("/listado")
    public String listarProveedores(Model model) {
        List<Proveedor> proveedores = proveedorService.getAll();
        model.addAttribute("proveedores", proveedores);
        return "/Home/Proveedor/listaProveedores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        proveedorService.delete(id);
        return "redirect:/proveedor/listado";
    }

 

    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable Long id,Model model){
        model.addAttribute("proveedor",proveedorService.busquedaPorId(id));
        return "/Home/Proveedor/detalles";
    }

       

    @GetMapping("/comentario/{idE}")
    public String comentario(@PathVariable("idE") Long id, Model model) {
        Proveedor proveedor = proveedorService.busquedaPorId(id);
        model.addAttribute("proveedor", proveedor);
        return "/Home/Proveedor/comentarios";
    }
    @PostMapping("/comentariosProveedor/{idE}")
    private String comentarioProveedor(@PathVariable("idE") Long id, @ModelAttribute Proveedor p, Model model) {
        Proveedor proveedor = proveedorService.busquedaPorId(id);
        model.addAttribute("proveedor",p);
        proveedor.setId(p.getId());
        proveedor.setComentarios(p.getComentarios());
        // Guarda el proveedor actualizado
        proveedorService.create(proveedor);
        return "redirect:/proveedor/detalles/{idE}";
    }

    @GetMapping("/editar/{idE}")
    public String editar(@PathVariable("idE") Long id, Model model) {
        Proveedor proveedor = proveedorService.busquedaPorId(id);
        model.addAttribute("proveedor", proveedor);
        return "/Home/proveedor/editarProveedor";
    }
    @PostMapping("/editarProveedor/{idE}")
    private String editarProveedor(@PathVariable("idE") Long id, @ModelAttribute Proveedor p, Model model) {
        Proveedor proveedor = proveedorService.busquedaPorId(id);
        model.addAttribute("proveedor",p);
        proveedor.setId(p.getId());
        proveedor.setNombreEmpresa(p.getNombreEmpresa());
        proveedor.setNombreContacto(p.getNombreContacto());
        proveedor.setApellidoContacto(p.getApellidoContacto());
        proveedor.setTelefono(p.getTelefono());
        proveedor.setMail(p.getMail());
        proveedor.setDireccion(p.getDireccion());
        proveedor.setRubro(p.getRubro());
        // Guarda el proveedor actualizado
        proveedorService.create(proveedor);
        return "redirect:/proveedor/detalles/{idE}";
    }


}
