package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data @Entity
public class User {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Username cannot be blank.")
	@Size(min=3, message="Minimum lenght: 3 characters.")
	private String name;
	
	@NotBlank(message="E-mail cannot be blank.")
	private String email;
	
	@NotBlank(message="Password cannot be blank.")
	@Min(value=8, message="Minimum lenght: 8 characters.")
	private String password;
	
}
