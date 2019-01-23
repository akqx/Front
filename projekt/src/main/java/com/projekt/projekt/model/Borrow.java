package com.projekt.projekt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "borrow")
@EntityListeners(AuditingEntityListener.class)
public class Borrow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idborrow;
	private String dateborrowed;
	private String datereturned;
	private int iduser;
	private int idbook;

	public Long getIdborrow() {
		return idborrow;
	}

	public void setIdborrow(Long idborrow) {
		this.idborrow = idborrow;
	}

	public String getDateborrowed() {
		return dateborrowed;
	}

	public void setDateborrowed(String dateborrowed) {
		this.dateborrowed = dateborrowed;
	}

	public String getDatereturned() {
		return datereturned;
	}

	public void setDatereturned(String datereturned) {
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
