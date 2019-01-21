package com.projekt.projekt.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bookcopies")
public class Bookcopies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idbookcopies;
	private int copiesnumber;

	public Long getIdbookcopies() {
		return idbookcopies;
	}

	public void setIdbookcopies(Long idbookcopies) {
		this.idbookcopies = idbookcopies;
	}

	public int getCopiesnumber() {
		return copiesnumber;
	}

	public void setCopiesnumber(int copiesnumber) {
		this.copiesnumber = copiesnumber;
	}

}
