package com.juntasalud.citasmedicas.mapper;

import org.springframework.stereotype.Component;

import com.juntasalud.citasmedicas.dto.CitaResponseDTO;
import com.juntasalud.citasmedicas.entity.Cita;

@Component
public class CitaMapper {
	public CitaResponseDTO toResponseDTO(Cita cita) {
		
		CitaResponseDTO dto = new CitaResponseDTO();
		
		dto.setIdCita(cita.getIdCita());
		dto.setFecha(cita.getFechaCita());
		dto.setHora(cita.getHoraCita());
		dto.setNroActoMedico(cita.getNroActoMedico());
		
		dto.setLugar(cita.getEstablecimiento().getNombreEstablecimiento());
		dto.setConsultorio(cita.getConsultorioCita());
		dto.setDireccion(cita.getDireccion());
		dto.setEspecialidad(cita.getEspecialidad().getNomEspecialidad());
		dto.setMedico(cita.getMedico());
		dto.setServicio(cita.getServicio().getNombreServicio());
		
		dto.setModalidad(cita.getModalidad());
		dto.setMedioVirtual(cita.getMedioVirtual());
		dto.setEstado(cita.getEstado());
		
		return dto;
	}
}
