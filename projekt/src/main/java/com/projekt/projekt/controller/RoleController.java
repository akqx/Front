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
import com.projekt.projekt.model.Role;
import com.projekt.projekt.repository.RoleRepository;

@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	// Get All roles
	@GetMapping("/role")
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}

	// Create a new role
	@PostMapping("/role")
	public Role createRole(@Valid @RequestBody Role role) {
		return roleRepository.save(role);
	}

	@GetMapping("/role/{id}")
	public Role getRoleById(@PathVariable(value = "id") Long idrole) {
		return roleRepository.findById(idrole).orElseThrow(() -> new ResourceNotFoundException("Role", "id", idrole));
	}

	@PutMapping("/role/{id}")
	public Role updateRole(@PathVariable(value = "id") Long idrole, @Valid @RequestBody Role roleDetails) {

		Role role = roleRepository.findById(idrole)
				.orElseThrow(() -> new ResourceNotFoundException("role", "id", idrole));

		role.setRolecategory(roleDetails.getRolecategory());

		Role updatedrole = roleRepository.save(role);
		return updatedrole;
	}

	// Delete a role
	@DeleteMapping("/role/{id}")
	public ResponseEntity<?> deleterole(@PathVariable(value = "id") Long idrole) {
		Role role = roleRepository.findById(idrole)
				.orElseThrow(() -> new ResourceNotFoundException("role", "id", idrole));

		roleRepository.delete(role);

		return ResponseEntity.ok().build();
	}

}
