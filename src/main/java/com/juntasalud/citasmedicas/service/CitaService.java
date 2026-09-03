package com.juntasalud.citasmedicas.service;

import java.util.List;

import com.juntasalud.citasmedicas.dto.CitaResponseDTO;

public interface CitaService {
	
	List<CitaResponseDTO> listarCitas();
	
	List<CitaResponseDTO> buscarPorEspecialidad(String nombreEspecialidad);
	
	List<CitaResponseDTO> listarProximasCitas();
	
	List<CitaResponseDTO> buscarPorEstablecimiento(String nombreEstablecimiento);
	
}
