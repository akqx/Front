package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Movie {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Integer year;
	private String director;

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public String getDirector() {
		return director;
	}

}
