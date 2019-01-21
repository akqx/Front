package com.projekt.projekt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrow")
public class Borrow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idborrow;
	private Date dateborrowed;
	private Date datereturned;
	private int iduser;
	private int idbook;

	public Long getIdborrow() {
		return idborrow;
	}

	public void setIdborrow(Long idborrow) {
		this.idborrow = idborrow;
	}

	public Date getDateborrowed() {
		return dateborrowed;
	}

	public void setDateborrowed(Date dateborrowed) {
		this.dateborrowed = dateborrowed;
	}

	public Date getDatereturned() {
		return datereturned;
	}

	public void setDatereturned(Date datereturned) {
		this.datereturned = datereturned;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdbook() {
		return idbook;
	}

	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}

}
