package com.learnkafka.controller;

import com.learnkafka.domain.Book;

public class LockDown {

	public LockDown() {
		try {
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void play(Book b) {

	}

	public static void main() {
		LockDown.play(getBook());

		LockDown.phone(() -> {
			System.out.println("dddddddd");
		});
	}

	public static Book getBook() {
		return null;
	}

	public static void phone(Samsung s) {

	}

	// Constructors
	/*
	 * 1: Default constructor mandtory for Hibernate and JPA bcz when you call
	 * repo.findByName( ) Hibernate tries to recreate the Java class. 2: Super must
	 * be first line. 3:Final must be delare in constructor itself. 4: Try catch
	 * inside a constructor allowed.
	 */

}
