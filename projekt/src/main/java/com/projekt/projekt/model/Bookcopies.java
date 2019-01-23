package com.projekt.projekt.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "bookcopies")
@EntityListeners(AuditingEntityListener.class)
public class Bookcopies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idbookcopies;
	private int copiesnumber;
	private int idbook;

	public int getIdbook() {
		return idbook;
	}

	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}

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

	public int updateBookCopiesDifference(int difference) {
		// TODO Auto-generated method stub
		 return copiesnumber + difference;
		
	}

	public Bookcopies orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
