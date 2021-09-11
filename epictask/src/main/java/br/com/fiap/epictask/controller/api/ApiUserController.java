package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

	@Autowired
	private UserRepository repository;

	@GetMapping()
	@Cacheable("users")
	public Page<User> index(@RequestParam(required=false) String name, @PageableDefault(page=0, size=10) Pageable pageable) {
		return name == null ? repository.findAll(pageable) : repository.findByNameLike("%" + name + "%", pageable);
	}

	@GetMapping("{id}")
	public ResponseEntity<User> get(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}

	@PostMapping()
	@CacheEvict(value="users", allEntries=true)
	public ResponseEntity<User> create(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
		repository.save(user);
		URI uri = uriBuilder.path("/api/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@DeleteMapping("{id}")
	@CacheEvict(value="users", allEntries=true)
	public ResponseEntity<User> delete(@PathVariable Long id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	@CacheEvict(value="users", allEntries=true)
	public ResponseEntity<User> update(@RequestBody @Valid User newUser, @PathVariable Long id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		User user = optional.get();
		user.setName(newUser.getName());
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		repository.save(user);
		return ResponseEntity.ok(user);
	}

}
