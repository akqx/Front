package com.projekt.projekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booksbycategory")
public class Booksbycategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idbooksbycategory;
	private int idbook;
	private int idcategory;

	public Long getIdbooksbycategory() {
		return idbooksbycategory;
	}

	public void setIdbooksbycategory(Long idbooksbycategory) {
		this.idbooksbycategory = idbooksbycategory;
	}

	public int getIdbook() {
		return idbook;
	}

	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}

	public int getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(int idcategory) {
		this.idcategory = idcategory;
	}

}
