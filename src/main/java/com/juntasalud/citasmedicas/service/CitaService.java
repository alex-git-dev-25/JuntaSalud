package com.juntasalud.citasmedicas.service;

import java.util.List;

import com.juntasalud.citasmedicas.entity.Cita;

public interface CitaService {
	
	List<Cita> listarCitas();
	
	List<Cita> buscarPorEspecialidad(String nombreEspecialidad);
	
	List<Cita> listarProximasCitas();
	
	List<Cita> buscarPorEstablecimiento(String nombreEstablecimiento);
	
}
