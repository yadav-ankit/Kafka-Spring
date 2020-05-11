package com.learnkafka.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.ToString;

@Entity
public class LibraryEvents {

	@Id
	@GeneratedValue
	private Integer libraryEventId;

	@NotNull
	@Valid
	@OneToOne
	@ToString.Exclude
	private Book book;
	
	
	
	public Book getBook() {
		int xyz = 0;
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getLibraryEventId() {
		return libraryEventId;
	}
	public void setLibraryEventId(Integer libraryEventId) {
		this.libraryEventId = libraryEventId;
	}
	
	
}
