package com.example.RaktarkezeloProgram.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ITAL")
public class Ital {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false , length=20)
	private String cikkSzam;
	
	@Column(nullable=false , length=100)
	private String nev;
	
	private float kiszerelesLiter;
	
	private int termekAra;
	
	private float alkoholTartalom;
	
	private int darabSzam;
	
	private int darabSzamMinimum;
	
	private Boolean isSelected = false;
}
