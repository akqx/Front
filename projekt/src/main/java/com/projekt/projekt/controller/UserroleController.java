package com.projekt.projekt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekt.projekt.exception.ResourceNotFoundException;
import com.projekt.projekt.model.Userrole;
import com.projekt.projekt.repository.UserroleRepository;

@RestController
@RequestMapping("/api")
public class UserroleController {

	@Autowired
	UserroleRepository userroleRepository;

	// Get All userroles
	@GetMapping("/userrole")
	public List<Userrole> getAllUserrole() {
		return userroleRepository.findAll();
	}

	// Create a new userrole
	@PostMapping("/userrole")
	public Userrole createUserrole(@Valid @RequestBody Userrole userrole) {
		return userroleRepository.save(userrole);
	}

	@GetMapping("/userrole/{id}")
	public Userrole getUserroleById(@PathVariable(value = "id") Long iduserrole) {
		return userroleRepository.findById(iduserrole).orElseThrow(() -> new ResourceNotFoundException("Userrole", "id", iduserrole));
	}

	@PutMapping("/userrole/{id}")
	public Userrole updateUserrole(@PathVariable(value = "id") Long iduserrole, @Valid @RequestBody Userrole userroleDetails) {

		Userrole userrole = userroleRepository.findById(iduserrole)
				.orElseThrow(() -> new ResourceNotFoundException("userrole", "id", iduserrole));

		userrole.setIduser(userroleDetails.getIduser());
		userrole.setIdrole(userroleDetails.getIdrole());

		Userrole updateduserrole = userroleRepository.save(userrole);
		return updateduserrole;
	}

	// Delete an userrole
	@DeleteMapping("/userrole/{id}")
	public ResponseEntity<?> deleteuserrole(@PathVariable(value = "id") Long iduserrole) {
		Userrole userrole = userroleRepository.findById(iduserrole)
				.orElseThrow(() -> new ResourceNotFoundException("userrole", "id", iduserrole));

		userroleRepository.delete(userrole);

		return ResponseEntity.ok().build();
	}
}
