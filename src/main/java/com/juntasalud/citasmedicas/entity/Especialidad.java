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
@Table(name = "tb_especialidad")
@Getter
@Setter
@NoArgsConstructor
public class Especialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEspecialidad;
	
	@Column(length=50,nullable=false,unique=true)
	private String nomEspecialidad;
	
}
