package com.juntasalud.citasmedicas.entity;

import java.util.HashSet;
import java.util.Set;

import com.juntasalud.citasmedicas.enums.ModalidadCita;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_servicio")
@Getter
@Setter
@NoArgsConstructor
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idServicio;
	
	@Column(length=50, nullable=false)
	private String nombreServicio;
	
	@Column(nullable = false)
	private boolean requiereEspecialidadYMedico;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="tb_servicio_modalidad", joinColumns = @JoinColumn(name="idServicio"))
	@Enumerated(EnumType.STRING)
	@Column(name="modalidad")
	private Set<ModalidadCita> modalidadesPermitidas = new HashSet<>();
	
}
