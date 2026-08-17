package com.juntasalud.citasmedicas.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juntasalud.citasmedicas.enums.EstadoCita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_cita")
@Getter
@Setter
@NoArgsConstructor
public class Cita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCita;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate fechaCita;
	
	@JsonFormat(pattern = "HH:mm")
	@Column(nullable=false)
	private LocalTime horaCita;

	@Column(length=10)
	private String consultorioCita;
	
	@Column(length=20)
	private String nroActomedico;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idEstablecimiento", nullable=false)
	private Establecimiento establecimiento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idEspecialidad", nullable=false)
	private Especialidad especialidad;
	
	@Column(length=60)
	private String nombreMedico;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTipoCita", nullable=false)
	private TipoCita tipoCita; 
	
	@Enumerated(EnumType.STRING)
	@Column(length=20, nullable=false)
	private EstadoCita estado;
	
	
	
}
