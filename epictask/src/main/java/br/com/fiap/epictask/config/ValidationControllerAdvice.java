package br.com.fiap.epictask.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationControllerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public List<ValidationFieldError> handler(MethodArgumentNotValidException e) {
		ArrayList<ValidationFieldError> list = new ArrayList<ValidationFieldError>();
		
		List<FieldError> errors = e.getBindingResult().getFieldErrors();
		errors.forEach(error -> {
			list.add(new ValidationFieldError(error.getField(), error.getDefaultMessage()));
		});
		return list;
	}
	
}
