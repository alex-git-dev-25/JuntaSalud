package com.juntasalud.citasmedicas.mapper;

import org.springframework.stereotype.Component;

import com.juntasalud.citasmedicas.dto.CitaRequestDTO;
import com.juntasalud.citasmedicas.dto.CitaResponseDTO;
import com.juntasalud.citasmedicas.entity.Cita;
import com.juntasalud.citasmedicas.entity.Especialidad;
import com.juntasalud.citasmedicas.entity.Establecimiento;
import com.juntasalud.citasmedicas.entity.Servicio;

@Component
public class CitaMapper {
	
	public CitaResponseDTO toResponseDTO(Cita cita) {
		
		CitaResponseDTO dto = new CitaResponseDTO();
		
		dto.setIdCita(cita.getIdCita());
		dto.setFecha(cita.getFechaCita());
		dto.setHora(cita.getHoraCita());
		dto.setServicio(cita.getServicio().getNombreServicio());
		dto.setModalidad(cita.getModalidad());
		dto.setEstado(cita.getEstado());
		dto.setNroActoMedico(cita.getNroActoMedico());
		
		dto.setLugar(cita.getEstablecimiento() != null
				? cita.getEstablecimiento().getNombreEstablecimiento()
				: null);
		dto.setConsultorio(cita.getConsultorioCita());
		dto.setDireccion(cita.getDireccion());
		
		dto.setMedioVirtual(cita.getMedioVirtual());
		dto.setEspecialidad(cita.getEspecialidad() != null
				? cita.getEspecialidad().getNomEspecialidad()
				:null);
		dto.setMedico(cita.getMedico());
		
		return dto;
	}
	
	public Cita toEntity(CitaRequestDTO request, Servicio servicio, 
							Establecimiento establecimiento, Especialidad especialidad) {
		Cita cita = new Cita();
		cita.setFechaCita(request.getFecha());
		cita.setHoraCita(request.getHora());
		cita.setConsultorioCita(request.getConsultorio());
		cita.setNroActoMedico(request.getNroActoMedico());
		cita.setEstablecimiento(establecimiento);
		cita.setEspecialidad(especialidad);
		cita.setMedico(request.getMedico());
		cita.setServicio(servicio);
		cita.setModalidad(request.getModalidad());
		cita.setDireccion(request.getDireccion());
		cita.setMedioVirtual(request.getMedioVirtual());
		return cita;
	}
}
