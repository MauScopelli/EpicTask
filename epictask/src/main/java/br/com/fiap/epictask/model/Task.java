package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data @Entity
public class Task {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Title cannot be blank.")
	private String title;
	
	@Size(min=5, message="Minimum lenght: 5 characters.")
	private String description;
	
	@Max(value=10, message="Maximum points: 10." )
	@Min(value=1, message ="Minimum points: 1.")
	private int points;
	
}
