package com.noel.act3.controllers;

import com.noel.act3.models.Artista;
import com.noel.act3.models.Cancion;
import com.noel.act3.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CancionApiController {

    @Autowired private CancionService service;

    @GetMapping("/artistas")
    public List<Artista> listarArtistas() { return service.listarArtistas(); }

    @PostMapping("/artistas")
    public Artista crearArtista(@RequestBody Artista artista) { return service.guardarArtista(artista); }

    @GetMapping("/canciones")
    public List<Cancion> listar() { return service.listar(); }

    @GetMapping("/canciones/{id}")
    public ResponseEntity<Cancion> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/canciones")
    public Cancion crear(@RequestBody Cancion cancion) { return service.guardar(cancion); }

    @PutMapping("/canciones/{id}")
    public ResponseEntity<Cancion> actualizar(@PathVariable Long id, @RequestBody Cancion datos) {
        return service.buscarPorId(id).map(c -> {
            c.setTitulo(datos.getTitulo());
            c.setGenero(datos.getGenero());
            c.setAnio(datos.getAnio());
            if (datos.getArtista() != null) c.setArtista(datos.getArtista());
            return ResponseEntity.ok(service.guardar(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/canciones/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
