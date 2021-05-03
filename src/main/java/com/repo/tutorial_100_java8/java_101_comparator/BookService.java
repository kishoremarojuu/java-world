package com.repo.tutorial_100_java8.java_101_comparator;

import java.util.List;

public class BookService {
	public List<Book> getBooksinSort() {
		List<Book> books = new BookDAO().getBooks();
		books.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

		return books;
	}

	public static void main(String[] args) {
		System.out.println(new BookService().getBooksinSort());
	}
}

