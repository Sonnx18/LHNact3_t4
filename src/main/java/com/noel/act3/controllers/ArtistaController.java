package com.noel.act3.controllers;

import com.noel.act3.models.Artista;
import com.noel.act3.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired private CancionService service;
    @Value("${app.nombre}") private String appNombre;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("artistas", service.listarArtistas());
        model.addAttribute("nuevo", new Artista());
        model.addAttribute("appNombre", appNombre);
        return "artistas";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Artista artista) {
        service.guardarArtista(artista);
        return "redirect:/artistas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminarArtista(id);
        return "redirect:/artistas";
    }
}
