package com.noel.act3.controllers;

import com.noel.act3.models.Cancion;
import com.noel.act3.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CancionController {

    @Autowired private CancionService service;

    @Value("${app.nombre}") private String appNombre;
    @Value("${app.autor}")  private String appAutor;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("appNombre", appNombre);
        model.addAttribute("appAutor", appAutor);
        return "index";
    }

    @GetMapping("/canciones")
    public String lista(Model model) {
        model.addAttribute("canciones", service.listar());
        model.addAttribute("appNombre", appNombre);
        return "lista";
    }

    @GetMapping("/canciones/nueva")
    public String formularioNueva(Model model) {
        model.addAttribute("cancion", new Cancion());
        model.addAttribute("artistas", service.listarArtistas());
        model.addAttribute("appNombre", appNombre);
        return "formulario";
    }

    @GetMapping("/canciones/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Cancion c = service.buscarPorId(id).orElseThrow();
        model.addAttribute("cancion", c);
        model.addAttribute("artistas", service.listarArtistas());
        model.addAttribute("appNombre", appNombre);
        return "formulario";
    }

    @PostMapping("/canciones/guardar")
    public String guardar(@ModelAttribute Cancion cancion,
                          @RequestParam Long artistaId) {
        service.buscarArtistaPorId(artistaId).ifPresent(cancion::setArtista);
        service.guardar(cancion);
        return "redirect:/canciones";
    }

    @GetMapping("/canciones/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/canciones";
    }

    @GetMapping("/canciones/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Cancion c = service.buscarPorId(id).orElseThrow();
        model.addAttribute("cancion", c);
        model.addAttribute("appNombre", appNombre);
        return "detalle";
    }
}
