package com.juntasalud.citasmedicas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tipo_cita")
@Getter
@Setter
@NoArgsConstructor
public class TipoCita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoCita;
	
	@Column(length=50, nullable=false)
	private String nombreTipoCita;
	
}
