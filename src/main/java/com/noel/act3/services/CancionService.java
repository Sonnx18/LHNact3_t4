package com.noel.act3.services;

import com.noel.act3.models.Artista;
import com.noel.act3.models.Cancion;
import com.noel.act3.repositories.ArtistaRepository;
import com.noel.act3.repositories.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    @Autowired private CancionRepository cancionRepo;
    @Autowired private ArtistaRepository artistaRepo;

    public List<Cancion> listar() { return cancionRepo.findAll(); }

    public Optional<Cancion> buscarPorId(Long id) { return cancionRepo.findById(id); }

    public Cancion guardar(Cancion c) { return cancionRepo.save(c); }

    public void eliminar(Long id) { cancionRepo.deleteById(id); }

    public List<Artista> listarArtistas() { return artistaRepo.findAll(); }

    public Optional<Artista> buscarArtistaPorId(Long id) { return artistaRepo.findById(id); }

    public Artista guardarArtista(Artista a) { return artistaRepo.save(a); }

    public void eliminarArtista(Long id) { artistaRepo.deleteById(id); }
}
