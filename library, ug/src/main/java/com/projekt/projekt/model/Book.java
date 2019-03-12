package com.projekt.projekt.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class Book implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idbook;
	@NotBlank(message = "{notblank.warring}")
	@Size(min = 1, max = 100, message = "{size.warring}")
	private String name;
	private Integer year;
	@NotBlank(message = "{notblank.warring}")
	@Size(min = 1, max = 100, message = "{size.warring}")
	private String author;
	@Size(min = 1, max = 1000, message = "{size.warring}")
	private String note;
	@Size(min = 1, max = 250, message = "{size.warring}")
	private String language;
	private Integer copies;

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getIdbook() {
		return idbook;
	}

	public void setIdbook(Long idbook) {
		this.idbook = idbook;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
