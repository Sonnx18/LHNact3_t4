package com.noel.act3.repositories;

import com.noel.act3.models.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository<Cancion, Long> {}
