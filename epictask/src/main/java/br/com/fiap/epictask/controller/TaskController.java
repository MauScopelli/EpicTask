package br.com.fiap.epictask.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

@Controller
public class TaskController {
	
	@Autowired
	private TaskRepository repository;
	
	@GetMapping("/tasks")
	public ModelAndView tasks() {
		ModelAndView modelAndView = new ModelAndView("tasks");
		modelAndView.addObject("tasks", repository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/tasks/new")
	public ModelAndView newTask(Task task) {
		return new ModelAndView("new-task");
	}
	
	@PostMapping("/tasks/new")
	public ModelAndView newTask(@Valid Task task, BindingResult result) {
		if (result.hasErrors()) return new ModelAndView("new-task");
		repository.save(task);
		return this.tasks();
	}
	
}
