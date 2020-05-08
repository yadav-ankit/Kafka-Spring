package com.learnkafka.domain;

public class LibraryEvents {

	
	private Integer libraryEventId;
	long hhj;

//	@NotNull
//	@Valid
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
