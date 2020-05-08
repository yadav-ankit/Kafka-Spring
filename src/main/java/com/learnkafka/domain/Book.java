package com.learnkafka.domain;

public class Book {

//	@NotNull
	private Integer bookId;

//	@NotBlank
	private String bookName;

//	@NotBlank
	private String bookAuthor;
	
	
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	
	
	
}
