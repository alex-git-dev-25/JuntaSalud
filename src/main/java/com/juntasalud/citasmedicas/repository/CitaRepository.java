package com.juntasalud.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juntasalud.citasmedicas.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long>{

}
