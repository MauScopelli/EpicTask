package br.com.fiap.epictask.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping("/users")
	public ModelAndView users() {
		ModelAndView modelAndView = new ModelAndView("users");
		modelAndView.addObject("users", repository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/signup")
	public ModelAndView register(User user) {
		return new ModelAndView("signup");
	}
	
	@PostMapping("/signup")
	public String register(@Valid User user, BindingResult result, RedirectAttributes ra) {
		if (result.hasErrors()) return "signup";
		repository.save(user);
		ra.addFlashAttribute("message", "The account has been created!");
		return "redirect:/";
	}
	
}
