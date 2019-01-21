package com.projekt.projekt.model;

import java.util.List;
import javax.persistence.JoinColumn;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idrole;
	private String rolecategory;

	public Long getIdrole() {
		return idrole;
	}

	public void setIdrole(Long idrole) {
		this.idrole = idrole;
	}

	public String getRolecategory() {
		return rolecategory;
	}

	public void setRolecategory(String rolecategory) {
		this.rolecategory = rolecategory;
	}

}
